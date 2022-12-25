package com.svu.bus.view.all;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.svu.bus.R;
import com.svu.bus.model.User;
import com.svu.bus.controller.InsertAction;
import com.svu.bus.databinding.ActivityRegisterBinding;


public class RegisterActivity extends AppCompatActivity {
ActivityRegisterBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.cirRegisterButton.setIndeterminateProgressMode(true);
        binding.cirRegisterButton.setOnClickListener(v ->{if(binding.cirRegisterButton.getProgress()==0) register();});
        binding.btnLogin.setOnClickListener(v ->{ startActivity(new Intent(this,LoginActivity.class));finish();});
    }

    private void register()
    { if (binding.cirRegisterButton.getProgress() == 0) {
        binding.cirRegisterButton.setProgress(50);
        //binding.cirRegisterButton.setEnabled(false);
    }

        if (binding.editTextEmail.getText().toString().isEmpty() || binding.editTextMobile.getText().toString().isEmpty() || binding.editTextName.getText().toString().isEmpty() || binding.editTextPassword.getText().toString().isEmpty())
    { binding.cirRegisterButton.setProgress(-1);
        new Handler().postDelayed(() -> {binding.cirRegisterButton.setProgress(0);},3000);
        Toast.makeText(this, "please enter all data and try again", Toast.LENGTH_SHORT).show();
        return;
    }
        if (binding.editTextPassword.getText().toString().length()<8)
        {binding.cirRegisterButton.setProgress(-1);
            new Handler().postDelayed(() -> {binding.cirRegisterButton.setProgress(0);},3000);
            Toast.makeText(this, "please password length > 8", Toast.LENGTH_SHORT).show();
            return;

        }

        new User()
                .setEmail(binding.editTextEmail.getText().toString())
                .setPassword(binding.editTextPassword.getText().toString())
                .setRule("user")
                .setName(binding.editTextName.getText().toString())
                .setPhone(binding.editTextMobile.getText().toString())
                .insert(new InsertAction() {
                    @Override
                    public void insertComplate(User user) {
                            binding.cirRegisterButton.setProgress(100);
new Handler().postDelayed(() -> {
startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
finish();
},3000);
                        Toast.makeText(RegisterActivity.this, "success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void insertError(String s) {
                        binding.cirRegisterButton.setProgress(-1);
                        new Handler().postDelayed(() -> {
                            binding.cirRegisterButton.setEnabled(true);
                            binding.cirRegisterButton.setProgress(0);

                        },3000);
                        Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_SHORT).show();

                    }
                });


    }
}
