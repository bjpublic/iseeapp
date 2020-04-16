package com.charlezz.databindingcomponentsample;

import androidx.lifecycle.Lifecycle;

public class ClickBindingComponent implements androidx.databinding.DataBindingComponent {

    private final ClickBindingImpl clickBinding;
    public ClickBindingComponent(Lifecycle lifecycle){
        clickBinding = new ClickBindingImpl(lifecycle);
    }

    //메소드이름 만드는 규칙에 유의 (예: get + 클래스 이름)
    public ClickBindingImpl getClickBinding() {
        return clickBinding;
    }
}
