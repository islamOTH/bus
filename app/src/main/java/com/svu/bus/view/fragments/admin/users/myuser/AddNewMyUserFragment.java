package com.svu.bus.view.fragments.admin.users.myuser;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.svu.bus.R;
import com.svu.bus.databinding.FragmentAddMyUserBinding;
import com.svu.bus.databinding.FragmentRegisterBinding;
import com.svu.bus.model.User;
import com.svu.bus.view.fragments.DaggerFragments;
import com.svu.bus.view.fragments.Fragments;
import com.svu.bus.view.viewmodel.UserViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class AddNewMyUserFragment extends Fragment
{
    private FragmentAddMyUserBinding binding;
    private Fragments fragments;
    @Inject
    public UserViewModel userViewModel;

    @Inject
    public AddNewMyUserFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {     binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_my_user, container, false);
          userViewModel.clearUser();
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
            {
                User user=new User()
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
            else{binding.cirRegisterButton.setProgress(100);
                userViewModel.getAllUser();
                new Handler().postDelayed(() -> { toAllMyUserFragment();},1000);
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


    public void toAllMyUserFragment()
    {   if (getActivity()
            .getSupportFragmentManager()!=null)
                 getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left)
                .replace(R.id.mainFrame,fragments.getAllMyUsersFragment())
                .commit();
    }



    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
