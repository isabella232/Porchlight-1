package com.locationlabs.porchlight.activity.setflowname;

import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.base.BaseInteractor;


public interface SetFlowNameInteractor extends BaseInteractor {

   UserFlow getCurrentNewFlow();

   void saveCurrentFlow(UserFlow flow);
}
