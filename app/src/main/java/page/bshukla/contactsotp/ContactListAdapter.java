package page.bshukla.contactsotp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import page.bshukla.contactsotp.util.jsonmodels.Contact;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder> {
    private static final String ARG_CONTACT_FULL_NAME = "contact_full_name";
    private static final String ARG_CONTACT_PHONE_NUMBER = "contact_phone_number";

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView mContactsNameView;
        private ContactViewHolder(View itemView) {
            super(itemView);
            mContactsNameView = itemView.findViewById(R.id.contact_name);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }
    }

    private LayoutInflater mLayoutInflater;
    private List<Contact> mContacts;
    private View.OnClickListener mOnItemClickListener;

    public ContactListAdapter(Context context, List<Contact> contacts) {
        mLayoutInflater = LayoutInflater.from(context);
        mContacts = contacts;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.contact_list_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        if (mContacts != null) {
            Contact currentContact = mContacts.get(position);
            holder.mContactsNameView.setText(currentContact.getFullName());

            ContactDetailFragment contactDetailsFragment = new ContactDetailFragment();
            Bundle args = new Bundle();
            args.putString(ARG_CONTACT_FULL_NAME, currentContact.getFullName());
            args.putString(ARG_CONTACT_PHONE_NUMBER, currentContact.getPhoneNumber());
            contactDetailsFragment.setArguments(args);
        } else {
            holder.mContactsNameView.setText("NULL");
        }
    }

    @Override
    public int getItemCount() {
        if (mContacts != null) {
            return mContacts.size();
        } else return 0;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }
}