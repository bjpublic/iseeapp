package com.charlezz.arch.util;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class ViewBindingHolder<VDB extends ViewDataBinding>
        extends RecyclerView.ViewHolder {
    private VDB binding;
    public ViewBindingHolder(@NonNull VDB binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ViewBindingHolder(Context context, @LayoutRes int layoutId) {
        this(DataBindingUtil.inflate(
                LayoutInflater.from(context),
                layoutId,
                null,
                false));
    }

    public VDB getBinding() {
        return binding;
    }
}
