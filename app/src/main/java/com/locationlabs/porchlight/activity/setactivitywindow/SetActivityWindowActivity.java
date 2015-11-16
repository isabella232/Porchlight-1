package com.locationlabs.porchlight.activity.setactivitywindow;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.locationlabs.porchlight.R;
import com.locationlabs.porchlight.activity.setflowname.SetFlowNameActivity;
import com.locationlabs.porchlight.util.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;


public class SetActivityWindowActivity extends BaseActivity implements SetActivityWindowView {

   private SetActivityWindowPresenter mPresenter;

   @Bind(R.id.start_time_field)
   public EditText mStartTimeField;

   @Bind(R.id.end_time_field)
   public EditText mEndTimeField;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_set_activity_window);

      mPresenter = new SetActivityWindowPresenterImpl(this);
   }

   @OnClick(R.id.save_activity_window_button)
   public void saveActivityWindow() {
      String startTime = mStartTimeField.getText().toString();
      String endTime = mEndTimeField.getText().toString();
      mPresenter.handleNewWindow(startTime, endTime);
   }

   @Override
   public void goToNextStep() {
      Intent intent = new Intent(this, SetFlowNameActivity.class);
      startActivity(intent);
      finish();
   }
}
