package com.svu.bus.helper;

import android.app.ProgressDialog;

import javax.inject.Inject;

public class MyDialog
{
    private ProgressDialog dialog;
    @Inject
    public MyDialog(ProgressDialog dialog) {
        this.dialog = dialog;
    }

    public ProgressDialog getDialog() {
        return dialog;
    }

    public MyDialog setDialog(ProgressDialog dialog) {
        this.dialog = dialog;
        return this;
    }
}
