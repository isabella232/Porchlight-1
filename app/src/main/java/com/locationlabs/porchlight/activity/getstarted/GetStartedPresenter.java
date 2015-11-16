package com.locationlabs.porchlight.activity.getstarted;

import android.content.Context;

import com.locationlabs.porchlight.util.base.BasePresenter;


public interface GetStartedPresenter extends BasePresenter {

   Context getActivity();

   void handleGetStartedClick();

}