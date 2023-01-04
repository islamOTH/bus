package com.svu.bus.helper;

import android.app.ProgressDialog;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.svu.bus.MyApp;
import com.svu.bus.model.User;
import com.svu.bus.view.viewmodel.UserViewModel;


import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

@Module
public class HelperModuel {
     @Provides
     public FirebaseDatabase providerFirebaseDatabase(){return FirebaseDatabase.getInstance();}
     @Provides
     public FirebaseFirestore providerFirebaseFirestore(){return FirebaseFirestore.getInstance();}
     @Provides
     public ArrayList<User> providerUserArrayList(){return new ArrayList<>();}
     @Provides
     public ProgressDialog providerDialog(){ProgressDialog dialog= new ProgressDialog(MyApp.getActivity());dialog.setTitle("please wait");dialog.setMessage("Loading");dialog.setCancelable(false);return dialog;}

}


