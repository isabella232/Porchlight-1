package com.locationlabs.porchlight.activity.setlocation;

import android.content.Context;

import com.google.android.gms.maps.model.LatLng;
import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.base.BasePresenterImpl;

/**
 * Add short description here
 */
public class SetLocationPresenterImpl extends BasePresenterImpl implements SetLocationPresenter {

   private SetLocationView mView;
   private SetLocationInteractor mInteractor;

   private UserFlow mUserFlow;

   public SetLocationPresenterImpl(SetLocationView view) {
      super(view);

      mView = view;
      mInteractor = new SetLocationInteractorImpl(this);

      mUserFlow = new UserFlow();
   }

   @Override
   public Context getActivity() {
      return mView.getContext();
   }

   @Override
   public void lookForAddress(String address) {
      mInteractor.requestAddressCoordinates(address);
   }

   @Override
   public void updateAddress(LatLng location) {
      if (location != null) {
         mView.updateMapLocation(location);
      } else {
         mView.showToast("Cannot find location");
      }
   }

   @Override
   public void saveCurrentLocation(LatLng location) {
      mUserFlow.setLatitude(location.latitude);
      mUserFlow.setLongitude(location.longitude);
      mView.showProgressDialog(null, null, false);
      mInteractor.saveCurrentLocation(mUserFlow);
   }

   @Override
   public void confirmDataSent(boolean isDataSent) {
      mView.dismissProgressDialog();
      mView.goToNextStep();
   }

   @Override
   public void onError(Exception e) {
      mView.showToast(e != null ? e.getMessage() : "Error");
   }
}

