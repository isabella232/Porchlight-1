package com.locationlabs.porchlight.activity.setflowname;

import com.locationlabs.porchlight.util.base.BasePresenter;


public interface SetFlowNamePresenter extends BasePresenter {

   void updateFlowNameAndMdn(String flowName, String watchlistedMdn);

   void confirmDataSent(boolean isDataSent);
}
