package com.locationlabs.porchlight.activity.setlocation;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.locationlabs.porchlight.PorchlightApplication;
import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.DataStore;
import com.locationlabs.porchlight.util.base.BaseInteractorImpl;

import java.io.IOException;
import java.util.List;


public class SetLocationInteractorImpl extends BaseInteractorImpl implements SetLocationInteractor {

   private SetLocationPresenter mPresenter;

   public SetLocationInteractorImpl(SetLocationPresenter presenter) {
      super(presenter);
      mPresenter = presenter;
   }

   @Override
   public void requestAddressCoordinates(final String address) {
      new AsyncTask<Void, Void, Address>() {

         @Override
         protected Address doInBackground(Void... params) {
            Geocoder geocoder = new Geocoder(mPresenter.getActivity());
            Address newAddress = null;
            try {
               /** Ideally should get a list of suggested addresses and display it to the user */
               List<Address> addressList = geocoder.getFromLocationName(address, 1);
               if (addressList.size() > 0) {
                  newAddress = geocoder.getFromLocationName(address, 1).get(0);
               }
            } catch (IOException e) {
               mPresenter.onError(e);
            }
            return newAddress;
         }

         @Override
         protected void onPostExecute(Address address) {
            super.onPostExecute(address);
            if (address == null) {
               mPresenter.updateAddress(null);
            } else {
               double latitude = address.getLatitude();
               double longitude = address.getLongitude();
               mPresenter.updateAddress(new LatLng(latitude, longitude));
            }
         }
      }.execute();
   }

   @Override
   public void saveCurrentLocation(UserFlow flow) {
      DataStore.setNewFlowData(PorchlightApplication.getContext(), flow);
      mPresenter.confirmDataSent(true);
   }

}
