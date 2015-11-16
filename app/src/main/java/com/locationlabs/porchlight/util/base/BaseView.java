package com.locationlabs.porchlight.util.base;

import android.content.Context;


public interface BaseView {

   Context getContext();

   void showToast(String message);

   void showProgressDialog(String title, String message, boolean cancelable);

   void dismissProgressDialog();
}