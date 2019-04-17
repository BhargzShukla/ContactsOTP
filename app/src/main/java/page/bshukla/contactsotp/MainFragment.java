package page.bshukla.contactsotp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import page.bshukla.contactsotp.viewmodels.MessageViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private MessageViewModel mMessageViewModel;

    private ViewPager mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    private TabLayout mTabLayout;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mViewPager = rootView.findViewById(R.id.viewpager_container);
        mViewPagerAdapter = new ViewPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout = rootView.findViewById(R.id.tab_layout);
        mTabLayout.setupWithViewPager(mViewPager);

        return rootView;
    }

}
