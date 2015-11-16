package com.locationlabs.porchlight.activity.main;

import android.support.v7.widget.RecyclerView;

import com.locationlabs.porchlight.PorchlightApplication;
import com.locationlabs.porchlight.activity.main.adapter.FlowsAdapter;
import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.base.BasePresenterImpl;

import java.util.ArrayList;
import java.util.List;


public class MainPresenterImpl extends BasePresenterImpl implements MainPresenter {

   private MainView mView;
   private MainInteractor mInteractor;

   private FlowsAdapter mFlowAdapter;

   public MainPresenterImpl(MainView view) {
      super(view);
      mView = view;

      /** Real interactor is commented because backend is currently down. Using mock instead */
      if (PorchlightApplication.isMocked()) {
         mInteractor = new MainInteractorMockImpl(this);
      } else {
         mInteractor = new MainInteractorImpl(this);
      }
   }

   @Override
   public RecyclerView.Adapter getFlowsAdapter() {
      mFlowAdapter = new FlowsAdapter(mView.getContext(), new ArrayList<UserFlow>());
      mInteractor.requestFlows();
      return mFlowAdapter;
   }

   @Override
   public void updateFlows(List<UserFlow> flows) {
      if (mFlowAdapter != null) {
         mView.showNoListMessage(false);
         mFlowAdapter.updateFlows(flows);
      } else {
         mView.showNoListMessage(true);
      }
   }

   @Override
   public void clearNewFlow() {
      mInteractor.clearNewFlow();
   }

   @Override
   public void onStop() {
      mInteractor.cancelBackgroundTasks();
   }

   @Override
   public void onError(Exception e) {
      mView.showToast(e != null ? e.getMessage() : "Error");
   }
}

