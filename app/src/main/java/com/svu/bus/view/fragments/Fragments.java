package com.svu.bus.view.fragments;

import com.svu.bus.model.User;
import com.svu.bus.view.adapter.user.UserAdapter;
import com.svu.bus.view.fragments.admin.AdminFragment;
import com.svu.bus.view.fragments.admin.users.controller.AddNewControllerFragment;
import com.svu.bus.view.fragments.admin.users.controller.AllMyControllerFragment;
import com.svu.bus.view.fragments.admin.users.driver.AddNewDriverFragment;
import com.svu.bus.view.fragments.admin.users.driver.AllMyDriversFragment;
import com.svu.bus.view.fragments.admin.users.myuser.AddNewMyUserFragment;
import com.svu.bus.view.fragments.admin.users.myuser.AllMyUsersFragment;
import com.svu.bus.view.fragments.admin.users.myuser.MyUserInfoFragment;
import com.svu.bus.view.fragments.alluser.LoginFragment;
import com.svu.bus.view.fragments.alluser.RegisterFragment;
import com.svu.bus.view.fragments.alluser.SplashFragment;
import com.svu.bus.view.fragments.users.user.UserFragment;
import com.svu.bus.view.viewmodel.UserViewModel;

import java.util.ArrayList;

import dagger.Component;

@Component(modules = FragmentModule.class)
public interface Fragments {
    LoginFragment getLoginFragment();
    RegisterFragment getRegisterFragment();
    SplashFragment getSplashFragment();
    AdminFragment getAdminFragment();
    AllMyUsersFragment getAllMyUsersFragment();
    AllMyDriversFragment getAllMyDriverFragment();
    AllMyControllerFragment getAllMyControllerFragment();
    UserViewModel getUserViewModel();
    ArrayList<User> getUserArrayList();
    AddNewMyUserFragment getAddNewMyUserFragment();
    MyUserInfoFragment getMyUserInfoFragment();
    AddNewDriverFragment getAddNewDriverFragment();
    UserAdapter getUserAdapter();
    AddNewControllerFragment getAddNewControllerFragment();
    UserFragment getUserFragment();

}
