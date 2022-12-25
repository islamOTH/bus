package com.svu.bus.view.all;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.svu.bus.R;
import com.svu.bus.databinding.ActivityLoginBinding;
import com.svu.bus.model.User;
import com.svu.bus.view.user.MainActivity;

import java.util.ArrayList;


public class LoginActivity extends AppCompatActivity {
   private ActivityLoginBinding binding;
   private SharedPreferences preferences;
   private SharedPreferences.Editor  editor;
   private String sharedPrefrenceName="db";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.cirLoginButton.setIndeterminateProgressMode(true);
        binding.btnRegister.setOnClickListener(v -> {startActivity(new Intent(this,RegisterActivity.class));finish();});
        binding.cirLoginButton.setOnClickListener(v -> {if (binding.cirLoginButton.getProgress()==0)loign();});
        preferences = this.getSharedPreferences(sharedPrefrenceName,MODE_PRIVATE);
        editor=preferences.edit();

    }

    private void loign()
    {binding.cirLoginButton.setProgress(50);
        if (binding.editTextEmail.getText().toString().isEmpty()|| binding.editTextPassword.getText().toString().isEmpty())
        { binding.cirLoginButton.setProgress(-1);
            new Handler().postDelayed(() -> {binding.cirLoginButton.setProgress(0);},3000);
            Toast.makeText(this, "please enter all data and try again", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<User> users=new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                            for (QueryDocumentSnapshot document : task.getResult())
                                 users.add(new User(document.getId()).fromMap(document.getData()));

                        if (users.size()==0)
                        {Toast.makeText(LoginActivity.this, "error in connection", Toast.LENGTH_SHORT).show();
                            binding.cirLoginButton.setProgress(-1);
                            new Handler().postDelayed(() -> {binding.cirLoginButton.setProgress(0);},3000);
                       return;
                        }

                         User myUser=null;
                        for (User user:users)
                            if (binding.editTextEmail.getText().toString().equals(user.getEmail())||binding.editTextEmail.getText().toString().equals(user.getPhone()))
                            {myUser=user;break; }




                        if (myUser !=null) {

                            if (myUser.getPassword().equals(binding.editTextPassword.getText().toString()))
                            {
                                Toast.makeText(LoginActivity.this, "login success", Toast.LENGTH_SHORT).show();
                                binding.cirLoginButton.setProgress(100);
                                final  String id=myUser.getId();
                                new Handler().postDelayed(() -> {
                                    editor.putString("id",id);
                                    editor.apply();
                                    Toast.makeText(LoginActivity.this,""+ id, Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    finish();
                                },1200);
                                return;
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, "error in password", Toast.LENGTH_SHORT).show();
                                binding.cirLoginButton.setProgress(-1);
                                new Handler().postDelayed(() -> {binding.cirLoginButton.setProgress(0);},3000);
                                return;
                            }

                        }
                        else {
                            Toast.makeText(LoginActivity.this, "error in email or phone", Toast.LENGTH_SHORT).show();
                            binding.cirLoginButton.setProgress(-1);
                            new Handler().postDelayed(() -> {binding.cirLoginButton.setProgress(0);},3000);
                            return;
                        }



                    }
                });


    }
}