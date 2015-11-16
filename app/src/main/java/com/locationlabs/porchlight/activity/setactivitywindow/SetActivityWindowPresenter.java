package com.locationlabs.porchlight.activity.setactivitywindow;

import com.locationlabs.porchlight.util.base.BasePresenter;


public interface SetActivityWindowPresenter extends BasePresenter {

   void handleNewWindow(String startTime, String endTime);

   void confirmDataSent(boolean dataSent);
}
