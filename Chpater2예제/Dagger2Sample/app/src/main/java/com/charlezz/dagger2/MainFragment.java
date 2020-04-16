package com.charlezz.dagger2;

import javax.inject.Inject;
import javax.inject.Named;

import android.content.Context;
import android.util.Log;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
    @Inject
    @Named("app")
    String appString;
    @Inject
    @Named("activity")
    String activityString;
    @Inject
    @Named("fragment")
    String fragmentString;

    @Override
    public void onAttach(Context context) {
        ((MainActivity)getActivity()).getComponent().mainFragmentComponentFactory().create(this, new MainFragmentModule()).inject(this);
        Log.e("MainFragment", appString);
        Log.e("MainFragment", activityString);
        Log.e("MainFragment", fragmentString);
        super.onAttach(context);
    }

}