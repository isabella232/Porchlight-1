package com.locationlabs.porchlight.activity.handlecall;

import com.locationlabs.porchlight.api.routecall.CallRouting;
import com.locationlabs.porchlight.util.base.BaseInteractor;


public interface HandleCallInteractor extends BaseInteractor {

   void handleCall(@CallRouting.Action String action, String routeAddress);
}
