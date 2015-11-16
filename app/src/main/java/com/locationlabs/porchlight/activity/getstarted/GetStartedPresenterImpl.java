package com.locationlabs.porchlight.activity.getstarted;

import android.content.Context;

import com.locationlabs.porchlight.util.base.BasePresenterImpl;


public class GetStartedPresenterImpl extends BasePresenterImpl implements GetStartedPresenter {

   private GetStartedView mView;
   private GetStartedInteractor mInteractor;

   public GetStartedPresenterImpl(GetStartedView view) {
      super(view);
      mView = view;
      mInteractor = new GetStartedInteractorImpl(this);

      shouldSkipGetStarted();
   }

   private void shouldSkipGetStarted() {
      if (mInteractor.hasUserAlreadyStartedTheApp()) {
         mView.goToUserFlows();
      }
   }

   @Override
   public Context getActivity() {
      return mView.getContext();
   }

   @Override
   public void handleGetStartedClick() {
      mInteractor.storeGetStarted();
      mView.goToUserFlows();
   }
}

