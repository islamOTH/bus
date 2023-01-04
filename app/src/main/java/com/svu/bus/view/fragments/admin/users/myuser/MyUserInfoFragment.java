package com.svu.bus.view.fragments.admin.users.myuser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.svu.bus.MyApp;
import com.svu.bus.R;
import com.svu.bus.databinding.FragmentMyUserInfoBinding;
import com.svu.bus.model.User;
import com.svu.bus.view.fragments.DaggerFragments;
import com.svu.bus.view.fragments.Fragments;
import com.svu.bus.view.viewmodel.UserViewModel;

import javax.inject.Inject;

public class MyUserInfoFragment extends Fragment
{   private FragmentMyUserInfoBinding binding;
    private Fragments fragments;

    @Inject
    public UserViewModel userViewModel;

    @Inject
    public MyUserInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {   binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_user_info, container, false);
        return initFragment();
    }

    private View initFragment() {
        binding.setFragment(this);
        fragments = DaggerFragments.create();
        userViewModel.userliveData.observe(getActivity(),user -> {
            if (user==null)toAllAdminFragment();
            else{
            binding.cirRegisterButton.setProgress(0);
            binding.setUser(user);

            }

        });
        return binding.getRoot();
    }

    public void toAllAdminFragment()
    {
        MyApp.getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.mainFrame, DaggerFragments.create().getAdminFragment())
                .commit();
    }
 public void save(User user)
 { if (binding.cirRegisterButton.getProgress()==0)
    {  binding.cirRegisterButton.setIndeterminateProgressMode(true);
        binding.cirRegisterButton.setProgress(50);
        user
                .setEmail(binding.editTextEmail.getText().toString())
                        .setName(binding.editTextName.getText().toString())
                                .setPassword(binding.editTextPassword.getText().toString())
                                        .setPhone(binding.editTextMobile.getText().toString());
        userViewModel.updateUser(user);
    }
 }

}
