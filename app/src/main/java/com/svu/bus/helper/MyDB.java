package com.svu.bus.helper;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.svu.bus.MyApp;
import com.svu.bus.model.BankAccount;
import com.svu.bus.model.User;
import com.svu.bus.view.fragments.DaggerFragments;
import com.svu.bus.view.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MyDB {
  private FirebaseDatabase firebaseDatabase;
  private FirebaseFirestore firebaseFirestore;
  private ArrayList<User> users;
  private ArrayList<User> myUsers;
  private ArrayList<User> myDrivers;
  private ArrayList<User> myControolers;
  private UserViewModel userViewModel;
  private MyDialog myDialog;


    @Inject
    public MyDB(MyDialog myDialog,FirebaseDatabase firebaseDatabase, FirebaseFirestore firebaseFirestore, ArrayList<User> users, ArrayList<User> myUsers, ArrayList<User> myDrivers, ArrayList<User> myControolers) {
        this.firebaseDatabase = firebaseDatabase;
        this.firebaseFirestore = firebaseFirestore;
        this.users = users;
        this.myUsers = myUsers;
        this.myDrivers = myDrivers;
        this.myControolers = myControolers;
        this.myDialog=myDialog;
       }


    public void deleteUser(User user){
        myDialog.getDialog().show();
        userViewModel= DaggerFragments.create().getUserViewModel();

        firebaseFirestore.collection("users").document(user.getId())
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        myDialog.getDialog().dismiss();
                        Toast.makeText(MyApp.getActivity(), "Success delete", Toast.LENGTH_SHORT).show();

                        getAllUser();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MyApp.getActivity(), "error in connection", Toast.LENGTH_SHORT).show();
                        myDialog.getDialog().dismiss();
                    }
                });
    }
    public void getOneUser( String id) {
        userViewModel= DaggerFragments.create().getUserViewModel();
        firebaseFirestore.collection("users")
                .document(id)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                    {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists())
                        {
                     User user=new User().setId(document.getId()).fromMap(document.getData());
                            userViewModel.userliveData.setValue(user);
                    }
                        else {
                            userViewModel.userliveData.setValue(null);
                        }

                    }
                    else {
                        userViewModel.userliveData.setValue(null);
                    }
                });

    }
    public void insertUser(User user){
        userViewModel= DaggerFragments.create().getUserViewModel();
    firebaseFirestore.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("bank").child(documentReference.getId());
                        myRef.setValue(new BankAccount().setPassword(user.getPassword()).setUserID(documentReference.getId()).setBalance(0));
                        userViewModel.userliveData.setValue(user.setId(documentReference.getId()));

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        userViewModel.userliveData.setValue(null);
                    }
                });
    }
    public void getAllUser() {
        userViewModel= DaggerFragments.create().getUserViewModel();
        users.clear();
     myUsers.clear();
     myControolers.clear();
     myDrivers.clear();
      firebaseFirestore.collection("users")
              .get()
              .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                  for (QueryDocumentSnapshot document : task.getResult()) {
                  users.add(new User().setId(document.getId()).fromMap(document.getData()));}

                    for (User user:users)
                    {   if (user.getRule().equals("user"))   myUsers.add(user);
                        if (user.getRule().equals("driver"))   myDrivers.add(user);
                        if (user.getRule().equals("controller"))   myControolers.add(user);
                    }

                    userViewModel.listUserLiveData.setValue(myUsers);
                    userViewModel.listDriverLiveData.setValue(myDrivers);
                    userViewModel.listControllerLiveData.setValue(myControolers);
                    userViewModel.listAllUserLiveData.setValue(users);





                } else {
                  Toast.makeText(MyApp.getContext(), "error in connection", Toast.LENGTH_SHORT).show();
                }
              });



    }
    public void getAllUserForLogin() {
        userViewModel= DaggerFragments.create().getUserViewModel();
        users.clear();
        users.clear();
        firebaseFirestore.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            users.add(new User().setId(document.getId()).fromMap(document.getData()));}
                        userViewModel.listAllUserForLoginLiveData.setValue(users);

                    } else {
                        Toast.makeText(MyApp.getContext(), "error in connection", Toast.LENGTH_SHORT).show();
                    }
                });



    }


    public void updateUser(User user) {
        userViewModel= DaggerFragments.create().getUserViewModel();

        firebaseFirestore
                .collection("users")
                .document(user.getId())
                .update(user.toMap())
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                    {userViewModel.getAllUser();
                    userViewModel.userliveData.setValue(user);}
                    else
                    {userViewModel.userliveData.setValue(null);
                        Toast.makeText(MyApp.getActivity(), "error in connection", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
