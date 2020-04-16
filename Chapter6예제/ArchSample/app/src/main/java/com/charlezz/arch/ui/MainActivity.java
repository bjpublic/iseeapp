package com.charlezz.arch.ui;

import javax.inject.Inject;
import javax.inject.Named;

import android.os.Bundle;
import android.widget.Toast;

import com.charlezz.arch.databinding.ActivityMainBinding;
import com.charlezz.arch.util.SingleLiveEvent;

import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;
//멤버 인젝션을 하기 위해 DaggerAppCompatActivity를 상속한다.
public class MainActivity extends DaggerAppCompatActivity {

    //바인딩 클래스 주입
    @Inject
    Lazy<ActivityMainBinding> binding;

    @Inject
    @Named("errorEvent")
    SingleLiveEvent<Throwable> errorEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 이 액티비티를 lifecycleOwner로 설정하여,
        // 생명주기에 안전하게 데이터바인딩을 할 수 있도록 한다.
        binding.get().setLifecycleOwner(this);
        errorEvent.observe(this, this::showErrorToast);
    }

    private void showErrorToast(Throwable throwable) {
        throwable.printStackTrace();
        showToast(throwable.getMessage());
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
