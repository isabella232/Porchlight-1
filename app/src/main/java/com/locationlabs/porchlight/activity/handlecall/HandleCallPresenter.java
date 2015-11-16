package com.locationlabs.porchlight.activity.handlecall;

import com.locationlabs.porchlight.util.base.BasePresenter;


public interface HandleCallPresenter extends BasePresenter {

   void handleAllowCall();

   void handleBlockCall();

   void handleAnswerCall();

}
