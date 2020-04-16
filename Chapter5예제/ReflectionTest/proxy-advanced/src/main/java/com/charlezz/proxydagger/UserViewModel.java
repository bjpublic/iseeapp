package com.charlezz.proxydagger;

import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserViewModel extends BaseObservable {

    private static final String[] names = {
            "Charles",
            "Chris",
            "Nick",
            "Runa",
            "Ray",
            "Sam",
            "James",
            "Andy",
            "Chloe",
            "Jay",
            "Jean",
            "Jack"
    };

    private UserClickListener listener;

    private ArrayList<User> users = new ArrayList<>();

    public List<User> getUsers(){
        return users;
    }

    public UserViewModel(UserClickListener listener){
        this.listener = listener;
        loadUsers();
    }

    public void loadUsers(){
        users.clear();
        for(String name : names){
            users.add(new User(name, listener));
        }
        notifyChange();
    }

    @BindingAdapter("users")
    public static void setUsers(RecyclerView recyclerView, List<User> items){
        if(items!=null && recyclerView.getAdapter() instanceof UserAdapter){
            UserAdapter adapter = (UserAdapter) recyclerView.getAdapter();
            adapter.setItems(items);
        }
    }


}
