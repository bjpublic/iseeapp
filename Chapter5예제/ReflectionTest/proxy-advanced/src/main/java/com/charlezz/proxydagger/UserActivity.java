package com.charlezz.proxydagger;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.charlezz.proxydagger.databinding.ActivityUserBinding;

import java.lang.reflect.Proxy;

public class UserActivity extends AppCompatActivity implements UserClickListener {

    public static final String TAG = UserActivity.class.getSimpleName();
    UserViewModel viewModel;

    Toast toast;
    ActivityUserBinding binding;
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        adapter = new UserAdapter();


        UserClickListener listener = (UserClickListener) Proxy.newProxyInstance(
                UserClickListener.class.getClassLoader(),
                new Class[]{UserClickListener.class},
                new UserInvocationHandler(this));

        viewModel = new UserViewModel(listener);
//        viewModel = new UserViewModel(this);

        binding.setLifecycleOwner(this);
        binding.recyclerView.setAdapter(adapter);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onUserClick(User user) {
        Log.e(TAG, "onItemClick:" + user.getName());
        toast.setText(user.getName());
        toast.show();
    }

}
