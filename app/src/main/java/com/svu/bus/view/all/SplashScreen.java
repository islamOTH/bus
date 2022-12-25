package com.svu.bus.view.all;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.svu.bus.R;
import com.svu.bus.databinding.ActivitySplashScreenBinding;
import com.svu.bus.view.user.MainActivity;

public class SplashScreen extends AppCompatActivity {
private ActivitySplashScreenBinding binding;
private SharedPreferences preferences;
    private String sharedPrefrenceName="db";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_splash_screen);

        //name of animation from anim directory
       Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_in_left);

        binding.imageView.startAnimation(animSlide);
        binding.textView.startAnimation(animSlide);
        binding.imageView.setVisibility(View.VISIBLE);


getSharedPreferences(sharedPrefrenceName,MODE_PRIVATE).edit().putString("id",null).apply();
        new Handler().postDelayed(() -> {binding.progressDialog.setVisibility(View.VISIBLE);
            new Handler().postDelayed(() -> {
                preferences=getSharedPreferences(sharedPrefrenceName,MODE_PRIVATE);
                String id=preferences.getString("id",null);
                if (id==null)startActivity(new Intent(this,LoginActivity.class));
                else startActivity(new Intent(this, MainActivity.class));
                finish();
            },1000);
            },500);


    }

}
