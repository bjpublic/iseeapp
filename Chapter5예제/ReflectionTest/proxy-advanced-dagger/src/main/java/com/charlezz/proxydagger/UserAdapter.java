package com.charlezz.proxydagger;

import androidx.databinding.DataBindingUtil;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.charlezz.proxydagger.databinding.ViewUserBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private List<User> items = null;


    public void setItems(List<User> items){
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewUserBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()),R.layout.view_user, viewGroup, false);
        return new UserHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder mainViewHolder, int i) {
        mainViewHolder.binding.setData(items.get(i));
        mainViewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return items==null? 0 : items.size();
    }

    static class UserHolder extends RecyclerView.ViewHolder{

        ViewUserBinding binding;

        public UserHolder(@NonNull ViewUserBinding binding ) {
            super(binding.getRoot());
            this.binding =binding;
        }
    }
}
