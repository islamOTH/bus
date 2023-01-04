package com.svu.bus.view.fragments.alluser;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.svu.bus.MyApp;
import com.svu.bus.R;
import com.svu.bus.databinding.FragmentLoginBinding;
import com.svu.bus.helper.DaggerHelper;
import com.svu.bus.helper.Helper;
import com.svu.bus.helper.MyDB;
import com.svu.bus.helper.MyRefrence;
import com.svu.bus.model.DaggerModel;
import com.svu.bus.model.User;
import com.svu.bus.view.fragments.DaggerFragments;
import com.svu.bus.view.fragments.Fragments;
import com.svu.bus.view.viewmodel.UserViewModel;


import javax.inject.Inject;


public class LoginFragment extends Fragment {
    private Fragments fragments;
    private FragmentLoginBinding binding;
    private UserViewModel userViewModel;
    private MyRefrence myRefrence;

    @Inject
    public LoginFragment(UserViewModel userViewModel) {this.userViewModel=userViewModel;}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {     binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        userViewModel.clear();
        userViewModel.listAllUserForLoginLiveData.observe(MyApp.getActivity(),users -> {
            String email = binding.editTextEmail.getText().toString();
            String password = binding.editTextPassword.getText().toString();
            for (User user:users) {
                if (user.getPassword().equals(password) && (user.getEmail().equals(email) || user.getPhone().equals(email))) {
                    binding.cirLoginButton.setProgress(100);
                    new Handler().postDelayed(() -> toUserFragment(), 1000);
                    myRefrence.insertId(user.getId());
                    userViewModel.userliveData.setValue(user);
                    return;
                }
            }

                Toast.makeText(MyApp.getActivity(), "email or password wrong", Toast.LENGTH_SHORT).show();
                binding.cirLoginButton.setProgress(-1);
                new Handler().postDelayed(() ->binding.cirLoginButton.setProgress(0) ,3000);

            });
         return initFragment();
    }
    public View initFragment()
    {fragments= DaggerFragments.create();
        myRefrence=DaggerHelper.create().getMyRefrence();
        binding.setFragment(this);
        return binding.getRoot();
    }

    public void login()
    {  if (binding.cirLoginButton.getProgress()==0)
    {   if (binding.editTextEmail.getText().toString().equals("admin")&&binding.editTextPassword.getText().toString().equals("admin"))
        {toAdminFragment(); return;}
        binding.cirLoginButton.setIndeterminateProgressMode(true);
        binding.cirLoginButton.setProgress(50);

        if (binding.editTextEmail.getText().toString().equals("")&&binding.editTextPassword.getText().toString().equals(""))
        {Toast.makeText(getActivity(), "please enter all data", Toast.LENGTH_SHORT).show();
            binding.cirLoginButton.setProgress(-1);
            new Handler().postDelayed(() -> binding.cirLoginButton.setProgress(0),3000);
            return;}
        userViewModel.getAllUserForLogin();

    }

    }

    private void toUserFragment()
    {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                .replace(R.id.mainFrame,fragments.getUserFragment())
                .commit();}


    public void toRegisterFragment()
    {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                .replace(R.id.mainFrame,fragments.getRegisterFragment())
                .commit();

    }

    public void toAdminFragment()
    {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.mainFrame,fragments.getAdminFragment())
                .commit();
    }



}