package com.svu.bus.view.fragments.admin;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.svu.bus.MyApp;
import com.svu.bus.R;
import com.svu.bus.databinding.FragmentAdminBinding;
import com.svu.bus.helper.DaggerHelper;
import com.svu.bus.helper.MyDialog;
import com.svu.bus.view.fragments.DaggerFragments;
import com.svu.bus.view.fragments.Fragments;
import com.svu.bus.view.viewmodel.UserViewModel;

import javax.inject.Inject;

public class AdminFragment extends Fragment {
   private FragmentAdminBinding binding;
   private Fragments fragments;
   private UserViewModel userViewModel;
   private MyDialog myDialog;

    @Inject
    public AdminFragment(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {     binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin, container, false);
        userViewModel.clear();

        myDialog= DaggerHelper.create().getMyDialog();
          myDialog.getDialog().show();
          userViewModel.listAllUserLiveData.observe(MyApp.getActivity(), users ->
          {
              myDialog.getDialog().dismiss();
          }
          );
          userViewModel.getAllUser();
          return initFragment();
    }

    private View initFragment() {
        binding.setFragment(this);
        fragments= DaggerFragments.create();
        return binding.getRoot();
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

   public void toUserFragment()
   {
               getActivity()
               .getSupportFragmentManager()
               .beginTransaction()
               .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
               .replace(R.id.mainFrame,fragments.getAllMyUsersFragment())
               .commit();
   }



    public void toDriverFragment()
    {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.mainFrame,fragments.getAllMyDriverFragment())
                .commit();
    }



    public void toControllerFragment()
    {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .addToBackStack(fragments.getAllMyUsersFragment().getClass().getName())
                .add(R.id.mainFrame,fragments.getAllMyControllerFragment())
                .commit();
    }


}
