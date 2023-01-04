package com.svu.bus.view.adapter.user;

import androidx.recyclerview.widget.RecyclerView;

import com.svu.bus.databinding.UserItemBinding;
import com.svu.bus.view.viewmodel.UserViewModel;

public  class MyViewHolder extends RecyclerView.ViewHolder
{
    private UserItemBinding binding;
    public MyViewHolder(UserItemBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

    public UserItemBinding getBinding() {
        return binding;
    }

    public MyViewHolder setBinding(UserItemBinding binding) {
        this.binding = binding;
        return this;
    }



}