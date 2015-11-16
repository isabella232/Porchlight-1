package com.locationlabs.porchlight.activity.setflowname;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.locationlabs.porchlight.R;
import com.locationlabs.porchlight.activity.main.MainActivity;
import com.locationlabs.porchlight.util.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;


public class SetFlowNameActivity extends BaseActivity implements SetFlowNameView {

   SetFlowNamePresenter mPresenter;

   @Bind(R.id.contact_number_field)
   EditText mContactMdnField;

   @Bind(R.id.flow_name_field)
   EditText mFlowNameField;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_set_flow_name);

      mPresenter = new SetFlowNamePresenterImpl(this);
   }

   @OnClick(R.id.save_flow_button)
   public void saveFlow() {
      mPresenter.updateFlowNameAndMdn(
            mFlowNameField.getText().toString(),
            mContactMdnField.getText().toString()
      );
   }

   @Override
   public void goToMainSreen() {
      Intent intent = new Intent(this, MainActivity.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
      startActivity(intent);
      finish();
   }
}
