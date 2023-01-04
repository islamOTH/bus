package com.svu.bus.helper;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.svu.bus.MyApp;
import com.svu.bus.R;

import javax.inject.Inject;

public class MyAnimation {
    private static final String TAG ="MyAnimation" ;

    @Inject
    public MyAnimation() {}
    public  Animation getSlideLeft() {return AnimationUtils.loadAnimation(MyApp.getContext(), R.anim.slide_in_left);}
    public  Animation getSlideRight() {return AnimationUtils.loadAnimation(MyApp.getContext(), R.anim.slide_in_right);}
    public  Animation getSty() {return AnimationUtils.loadAnimation(MyApp.getContext(), R.anim.stay);}

}
