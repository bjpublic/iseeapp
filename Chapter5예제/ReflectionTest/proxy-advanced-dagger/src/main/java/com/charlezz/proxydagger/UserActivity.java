package com.charlezz.proxydagger;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.charlezz.proxydagger.databinding.ActivityUserBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class UserActivity extends DaggerAppCompatActivity implements UserClickListener {

    public static final String TAG = UserActivity.class.getSimpleName();
    @Inject
    ActivityUserBinding binding;

    @Inject
    UserAdapter adapter;

    @Inject
    UserViewModel viewModel;

    @Inject
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setLifecycleOwner(this);
        binding.recyclerView.setAdapter(adapter);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onUserClick(User user) {
        Log.e(TAG,"onItemClick:"+user.getName());
        toast.setText(user.getName());
        toast.show();
    }

}
