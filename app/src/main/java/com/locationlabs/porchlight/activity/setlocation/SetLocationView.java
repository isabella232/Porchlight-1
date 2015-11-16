package com.locationlabs.porchlight.activity.setlocation;

import com.google.android.gms.maps.model.LatLng;
import com.locationlabs.porchlight.util.base.BaseView;

/**
 * Add short description here
 */
public interface SetLocationView extends BaseView {

   void updateMapLocation(LatLng location);

   void goToNextStep();
}
