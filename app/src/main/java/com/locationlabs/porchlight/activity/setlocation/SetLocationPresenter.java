package com.locationlabs.porchlight.activity.setlocation;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.locationlabs.porchlight.util.base.BasePresenter;

/**
 * Add short description here
 */
public interface SetLocationPresenter extends BasePresenter {

   Context getActivity();

   void lookForAddress(String address);

   void updateAddress(LatLng location);

   void saveCurrentLocation(LatLng location);

   void confirmDataSent(boolean isDataSent);
}

