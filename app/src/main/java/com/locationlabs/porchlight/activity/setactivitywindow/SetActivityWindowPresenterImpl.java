package com.locationlabs.porchlight.activity.setactivitywindow;

import com.locationlabs.porchlight.api.model.ActivityWindow;
import com.locationlabs.porchlight.util.base.BasePresenterImpl;


public class SetActivityWindowPresenterImpl extends BasePresenterImpl
      implements SetActivityWindowPresenter {

   private SetActivityWindowView mView;
   private SetActivityWindowInteractor mInteractor;

   public SetActivityWindowPresenterImpl(SetActivityWindowView view) {
      super(view);
      mView = view;
      mInteractor = new SetActivityWindowInteractorImpl(this);
   }

   @Override
   public void onError(Exception e) {
      // Not really anything to do here, but could be useful
   }

   @Override
   public void handleNewWindow(String startTime, String endTime) {
      mView.showProgressDialog(null, null, false);
      mInteractor.saveActivityWindow(new ActivityWindow("", startTime, endTime));
   }

   @Override
   public void confirmDataSent(boolean dataSent) {
      mView.dismissProgressDialog();
      mView.goToNextStep();
   }
}
