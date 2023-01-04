package com.svu.bus.view.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.svu.bus.model.Pay;

import java.util.ArrayList;

public class PayViewModel extends ViewModel {
    public MutableLiveData<ArrayList<Pay>> listMutableLiveData=new MutableLiveData<>();
    public void clear() { this.listMutableLiveData = new MutableLiveData<>();}

}
