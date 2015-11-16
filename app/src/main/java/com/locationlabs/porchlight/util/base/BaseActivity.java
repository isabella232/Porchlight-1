package com.locationlabs.porchlight.util.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.locationlabs.porchlight.R;

import butterknife.ButterKnife;


public class BaseActivity extends AppCompatActivity implements BaseView {

   protected ProgressDialog mProgressDialog;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
   }

   @Override
   public void setContentView(int layoutResID) {
      super.setContentView(layoutResID);
      ButterKnife.bind(this);
   }

   @Override
   protected void onStop() {
      super.onStop();
      if (mProgressDialog != null) {
         mProgressDialog.dismiss();
      }
   }

   @Override
   public Context getContext() {
      return this;
   }

   @Override
   public void showToast(String message) {
      Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
   }

   @Override
   public void showProgressDialog(String title, String message, boolean cancelable) {
      if (message == null) {
         message = getString(R.string.progress_dialog_loading);
      }
      mProgressDialog = ProgressDialog.show(this, title, message, false, cancelable);
   }

   @Override
   public void dismissProgressDialog() {
      mProgressDialog.dismiss();
   }
}

