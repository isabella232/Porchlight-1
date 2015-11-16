package com.locationlabs.porchlight.activity.getstarted;

import android.content.Intent;
import android.os.Bundle;

import com.locationlabs.porchlight.R;
import com.locationlabs.porchlight.activity.main.MainActivity;
import com.locationlabs.porchlight.util.base.BaseActivity;

import butterknife.OnClick;

public class GetStartedActivity extends BaseActivity implements GetStartedView {

   private GetStartedPresenter mPresenter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_get_started);

      mPresenter = new GetStartedPresenterImpl(this);
   }

   @OnClick(R.id.get_started_button)
   public void getStartedClick() {
      mPresenter.handleGetStartedClick();
   }

   @Override
   public void goToUserFlows() {
      Intent intent = new Intent(this, MainActivity.class);
      startActivity(intent);
      finish();
   }
}
