package com.svu.bus.view.fragments;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.svu.bus.MyApp;
import com.svu.bus.model.User;
import com.svu.bus.view.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {

    @Provides
    public ArrayList<User> providerUserArrayList(){return new ArrayList<>();}

    @Provides
    public UserViewModel providerUserViewModel(){return new ViewModelProvider(MyApp.getActivity()).get(UserViewModel.class);}
}
