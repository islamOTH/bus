package com.svu.bus.view.adapter.user;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.svu.bus.R;
import com.svu.bus.databinding.UserItemBinding;
import com.svu.bus.model.User;
import com.svu.bus.view.viewmodel.UserViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class UserAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private ArrayList<User> users;
    @Inject
    public  UserViewModel userViewModel;

    @Inject
    public UserAdapter(ArrayList<User> users) {this.users = users;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding binding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_item,parent,false);
        binding.setViewModel(userViewModel);
        return new MyViewHolder(binding);    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    { holder.getBinding().setUser(users.get(position));}
    public void setUsers(ArrayList<User> users) {
        this.users = users;

        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


}
