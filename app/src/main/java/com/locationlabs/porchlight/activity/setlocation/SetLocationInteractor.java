package com.locationlabs.porchlight.activity.setlocation;

import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.base.BaseInteractor;

/**
 * Add short description here
 */
public interface SetLocationInteractor extends BaseInteractor {

   void requestAddressCoordinates(String address);

   void saveCurrentLocation(UserFlow flow);
}
