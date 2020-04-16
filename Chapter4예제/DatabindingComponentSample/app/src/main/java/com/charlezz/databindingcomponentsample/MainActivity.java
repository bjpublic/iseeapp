package com.charlezz.databindingcomponentsample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.charlezz.databindingcomponentsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main,
                new ClickBindingComponent(getLifecycle())
        );

        binding.setClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.e(TAG, "Clicked");
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }
}
