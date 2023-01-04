package com.svu.bus.view.fragments.alluser;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.svu.bus.R;
import com.svu.bus.databinding.FragmentRegisterBinding;
import com.svu.bus.model.User;
import com.svu.bus.view.fragments.DaggerFragments;
import com.svu.bus.view.fragments.Fragments;
import com.svu.bus.view.viewmodel.UserViewModel;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private Fragments fragments;
    @Inject
    public UserViewModel userViewModel;

    @Inject
    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {     binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
          userViewModel.clear();
          return initFragment();
    }
    public View initFragment()
    {
        fragments= DaggerFragments.create();
        binding.setFragment(this);
        binding.cirRegisterButton.setIndeterminateProgressMode(true);
        return binding.getRoot();
    }
    public void register()
    {
        if (binding.cirRegisterButton.getProgress()==0)
        {
            binding.cirRegisterButton.setProgress(50);
            if (isTrue())
            {User user=new User()
                    .setEmail(binding.editTextEmail.getText().toString())
                    .setPassword(binding.editTextPassword.getText().toString())
                    .setName(binding.editTextName.getText().toString())
                    .setPhone(binding.editTextMobile.getText().toString())
                    .setRule("user");
                    userViewModel.insertUser(user);


            }
            else {
           binding.cirRegisterButton.setProgress(-1);
           new Handler().postDelayed(() -> {binding.cirRegisterButton.setProgress(0);},3000);
            }
        }
        userViewModel.userliveData.observe(getActivity(),user -> {
            if (user==null){
                Toast.makeText(getActivity(), "error in connection", Toast.LENGTH_SHORT).show();
                binding.cirRegisterButton.setProgress(-1);
                new Handler().postDelayed(() -> {
                    binding.cirRegisterButton.setProgress(0);
                },3000);

            }
            else{
                toLoginFragment();
            }
        });

    }

    private boolean isTrue()
    {
        if (binding.editTextEmail.getText().toString().equals("") || binding.editTextMobile.getText().toString().equals("") || binding.editTextPassword.getText().toString().equals("") || binding.editTextName.getText().toString().equals(""))
        {
            Toast.makeText(getActivity(), "enter all data please", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!binding.editTextMobile.getText().toString().substring(0,2).equals("09"))
        {
            Toast.makeText(getActivity(), "error in phone", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!validate(binding.editTextEmail.getText().toString()))
        {
            Toast.makeText(getActivity(), "error in email", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;


    }


    public void toLoginFragment()
    {
                 getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.mainFrame,fragments.getLoginFragment())
                .commit();

    }



    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
