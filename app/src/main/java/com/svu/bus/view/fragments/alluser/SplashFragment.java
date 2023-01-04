package com.svu.bus.view.fragments.alluser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;

import com.svu.bus.MyApp;
import com.svu.bus.R;
import com.svu.bus.databinding.FragmentSplashBinding;
import com.svu.bus.helper.DaggerHelper;
import com.svu.bus.helper.MyAnimation;
import com.svu.bus.view.fragments.DaggerFragments;
import com.svu.bus.view.fragments.Fragments;
import com.svu.bus.view.viewmodel.UserViewModel;

import javax.inject.Inject;

public class SplashFragment extends Fragment {
    private FragmentSplashBinding binding;

    private Fragments fragment;
    @Inject
    public MyAnimation myAnimation;
    @Inject
    public UserViewModel userViewModel;

     @Inject
    public SplashFragment()
     {
     }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {     binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false);
          userViewModel.clear();
          return initFragment();
    }

public View initFragment()
{ fragment= DaggerFragments.create();
    binding.imageView.startAnimation(myAnimation.getSlideLeft());
    binding.textView.startAnimation(myAnimation.getSlideLeft());
    binding.progressDialog.setVisibility(View.VISIBLE);
    userViewModel.getOneUserFromRefrence();
    goToFragment();
    return binding.getRoot();
}

    private void goToFragment() {
         userViewModel.userliveData
                 .observe(getActivity(),user -> {
                     if( user==null)
                         getActivity().getSupportFragmentManager()
                                 .beginTransaction()
                                 .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                                 .replace(R.id.mainFrame,fragment.getLoginFragment())
                                 .commit();
                     else {

                         getActivity().getSupportFragmentManager()
                                 .beginTransaction()
                                 .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right)
                                 .replace(R.id.mainFrame,fragment.getUserFragment())
                                 .commit();
                       }
                 });
    }

}
