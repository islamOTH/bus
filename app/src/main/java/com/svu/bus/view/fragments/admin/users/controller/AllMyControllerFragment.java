package com.svu.bus.view.fragments.admin.users.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.svu.bus.R;
import com.svu.bus.databinding.FragmentAllMyControllerBinding;
import com.svu.bus.databinding.FragmentAllMyDriverBinding;
import com.svu.bus.view.adapter.user.UserAdapter;
import com.svu.bus.view.fragments.DaggerFragments;
import com.svu.bus.view.fragments.Fragments;
import com.svu.bus.view.viewmodel.UserViewModel;

import javax.inject.Inject;

public class AllMyControllerFragment extends Fragment
{
    private FragmentAllMyControllerBinding binding;
    private UserViewModel userViewModel;
    private Fragments fragments;
    private UserAdapter userAdapter;
    @Inject
    public AllMyControllerFragment(UserViewModel userViewModel, UserAdapter userAdapter) {
        this.userViewModel = userViewModel;
        this.userAdapter = userAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {   binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_my_controller, container, false);
        binding.rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.setAdapter(userAdapter);
        userViewModel=new ViewModelProvider(getActivity()).get(UserViewModel.class);
        userViewModel.listControllerLiveData.observe(getActivity(),
                users ->{binding.getAdapter().setUsers(users);} );



        return initFragment();
    }

    private View initFragment() {
        binding.setFragment(this);
        fragments= DaggerFragments.create();
        return binding.getRoot();
    }

    public void toAddNewMyControllerFragment()
    {
                 getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.mainFrame,fragments.getAddNewControllerFragment())
                .commit();
    }


    public void toAdminFragment()
    {  if (getActivity().getSupportFragmentManager()!=null)
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.mainFrame,fragments.getAdminFragment())
                .commit();
    }


}
