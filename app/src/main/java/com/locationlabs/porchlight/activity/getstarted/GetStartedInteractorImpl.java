package com.locationlabs.porchlight.activity.getstarted;

import com.locationlabs.porchlight.util.DataStore;
import com.locationlabs.porchlight.util.base.BaseInteractorImpl;


public class GetStartedInteractorImpl extends BaseInteractorImpl implements GetStartedInteractor {

   private GetStartedPresenter mPresenter;

   public GetStartedInteractorImpl(GetStartedPresenter presenter) {
      super(presenter);
      mPresenter = presenter;
   }

   @Override
   public void storeGetStarted() {
      DataStore.setFirstRun(mPresenter.getActivity(), true);
   }

   @Override
   public boolean hasUserAlreadyStartedTheApp() {
      return DataStore.getFirstRun(mPresenter.getActivity());
   }
}