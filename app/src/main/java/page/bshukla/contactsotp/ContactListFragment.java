package page.bshukla.contactsotp;


import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import page.bshukla.contactsotp.util.JSONParser;
import page.bshukla.contactsotp.util.jsonmodels.Contact;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {
    private contactListFetcher contactListFetcherThread;
    private static List<Contact> mContacts = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public ContactListFragment() {
        // Required empty public constructor
    }

    private View.OnClickListener onItemClickListener = v -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
        int position = viewHolder.getAdapterPosition();
        Contact contact = mContacts.get(position);
        Toast.makeText(getContext(), contact.getFullName(), Toast.LENGTH_SHORT).show();
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_contact_list, container, false);
        contactListFetcherThread = new contactListFetcher(ContactListFragment.this, rootView);

        RecyclerView mContactsRecyclerView = rootView.findViewById(R.id.recyclerview_contacts);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContactsRecyclerView.getContext(),
                new LinearLayoutManager(getContext()).getOrientation());
        RecyclerView.Adapter contactListAdapter = new ContactListAdapter(getActivity(), mContacts);
        ((ContactListAdapter) contactListAdapter).setOnItemClickListener(onItemClickListener);
        mContactsRecyclerView.setAdapter(contactListAdapter);
        mContactsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mContactsRecyclerView.addItemDecoration(dividerItemDecoration);
        mContactsRecyclerView.setHasFixedSize(true);

        contactListFetcherThread.execute();
        contactListAdapter.notifyDataSetChanged();
        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + "must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        contactListFetcherThread.cancel(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    private class contactListFetcher extends AsyncTask<Void, Void, Void> {
        private WeakReference<ContactListFragment> contactsFragmentWeakReference;
        private View rootView;
        ProgressBar mContactProgressBar;

        contactListFetcher(ContactListFragment context, View rootView) {
            contactsFragmentWeakReference = new WeakReference<>(context);
            this.rootView = rootView;
        }

        @Override
        protected void onPreExecute() {
            mContactProgressBar = rootView.findViewById(R.id.contact_list_progressbar);
            mContactProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            JSONParser jsonParser = new JSONParser(rootView.getResources(), R.raw.contacts);
            mContacts = jsonParser.constructUsingGson(new TypeToken<ArrayList<Contact>>() {
            }.getType());
            mContacts.sort(Comparator.comparing(Contact::getFirstName));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mContactProgressBar.setVisibility(View.GONE);
        }
    }

}
