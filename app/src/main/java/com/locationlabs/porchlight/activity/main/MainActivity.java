package com.locationlabs.porchlight.activity.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.locationlabs.porchlight.R;
import com.locationlabs.porchlight.activity.setlocation.SetLocationActivity;
import com.locationlabs.porchlight.util.base.BaseActivity;
import com.locationlabs.porchlight.util.recyclerview.SimpleDividerItemDecoration;

import butterknife.Bind;
import butterknife.OnClick;
import jp.wasabeef.recyclerview.animators.FadeInAnimator;


public class MainActivity extends BaseActivity implements MainView {

   private MainPresenter mPresenter;

   @Bind(R.id.no_flow_msg)
   TextView mNoFlowMessage;

   @Bind(R.id.flow_list)
   RecyclerView mFlowList;
   RecyclerView.LayoutManager mLayoutManager;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      mPresenter = new MainPresenterImpl(this);
   }

   @Override
   protected void onResume() {
      super.onResume();
      setupFlowsRecyclerView();
   }

   @Override
   public void showNoListMessage(boolean show) {
      if (show) {
         mNoFlowMessage.setVisibility(View.VISIBLE);
         mFlowList.setVisibility(View.GONE);
      } else {
         mNoFlowMessage.setVisibility(View.GONE);
         mFlowList.setVisibility(View.VISIBLE);
      }
   }

   @Override
   protected void onStop() {
      super.onStop();
      mPresenter.onStop();
   }

   @OnClick(R.id.create_flow_button)
   public void goToMap() {
      // Clear flow
      mPresenter.clearNewFlow();

      // Start new flow
      Intent intent = new Intent(this, SetLocationActivity.class);
      startActivity(intent);
   }

   private void setupFlowsRecyclerView() {
      mLayoutManager = new LinearLayoutManager(this);
      ((LinearLayoutManager) mLayoutManager).setOrientation(LinearLayoutManager.VERTICAL);
      mFlowList.setLayoutManager(mLayoutManager);
      mFlowList.addItemDecoration(new SimpleDividerItemDecoration(this));
      mFlowList.setItemAnimator(new FadeInAnimator());
      mFlowList.setAdapter(mPresenter.getFlowsAdapter());
   }
}
