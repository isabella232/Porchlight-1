package com.locationlabs.porchlight.activity.getstarted;

import com.locationlabs.porchlight.util.base.BaseInteractor;


public interface GetStartedInteractor extends BaseInteractor {

   void storeGetStarted();

   boolean hasUserAlreadyStartedTheApp();
}
