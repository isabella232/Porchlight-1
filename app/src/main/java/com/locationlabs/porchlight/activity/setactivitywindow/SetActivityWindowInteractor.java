package com.locationlabs.porchlight.activity.setactivitywindow;

import com.locationlabs.porchlight.api.model.ActivityWindow;
import com.locationlabs.porchlight.util.base.BaseInteractor;


public interface SetActivityWindowInteractor extends BaseInteractor {

   void saveActivityWindow(ActivityWindow activityWindow);
}
