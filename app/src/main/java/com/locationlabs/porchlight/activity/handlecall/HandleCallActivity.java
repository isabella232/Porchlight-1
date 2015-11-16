package com.locationlabs.porchlight.activity.handlecall;

import android.os.Bundle;

import com.locationlabs.porchlight.R;
import com.locationlabs.porchlight.util.base.BaseActivity;

import butterknife.OnClick;

/**
 * View for handling call routing
 */
public class HandleCallActivity extends BaseActivity implements HandleCallView {

   private HandleCallPresenter mPresenter;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_handle_call);
      mPresenter = new HandleCallPresenterImpl(this);
   }

   @OnClick(R.id.allow_call_button)
   public void allowCall() {
      mPresenter.handleAllowCall();
   }

   @OnClick(R.id.block_call_button)
   public void blockCall() {
      mPresenter.handleBlockCall();
   }

   @OnClick(R.id.answer_call_button)
   public void answerCall() {
      mPresenter.handleAnswerCall();
   }

   @Override
   public void mFinish() {
      finish();
   }
}
