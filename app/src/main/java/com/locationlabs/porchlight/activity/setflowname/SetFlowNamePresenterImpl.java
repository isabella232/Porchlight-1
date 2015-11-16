package com.locationlabs.porchlight.activity.setflowname;

import com.locationlabs.porchlight.PorchlightApplication;
import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.base.BasePresenterImpl;


public class SetFlowNamePresenterImpl extends BasePresenterImpl implements SetFlowNamePresenter {

   SetFlowNameView mView;
   SetFlowNameInteractor mInteractor;

   public SetFlowNamePresenterImpl(SetFlowNameView view) {
      super(view);
      mView = view;

      if (PorchlightApplication.isMocked()) {
         mInteractor = new SetFlowNameInteractorMockImpl(this);
      } else {
         mInteractor = new SetFlowNameInteractorImpl(this);
      }
   }

   @Override
   public void updateFlowNameAndMdn(String flowName, String watchlistedMdn) {
      UserFlow flow = mInteractor.getCurrentNewFlow();
      flow.setName(flowName);
      flow.setChildMdn(watchlistedMdn);

      mView.showProgressDialog(null, null, false);
      mInteractor.saveCurrentFlow(flow);
   }

   @Override
   public void confirmDataSent(boolean isDataSent) {
      mView.dismissProgressDialog();
      if (isDataSent) {
         mView.goToMainSreen();
      } else {
         // TODO: handle this case
      }
   }
}
