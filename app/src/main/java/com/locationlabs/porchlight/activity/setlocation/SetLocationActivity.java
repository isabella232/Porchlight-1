package com.locationlabs.porchlight.activity.setlocation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.locationlabs.porchlight.R;
import com.locationlabs.porchlight.activity.setactivitywindow.SetActivityWindowActivity;
import com.locationlabs.porchlight.util.base.BaseActivity;

import butterknife.Bind;
import butterknife.OnClick;


public class SetLocationActivity extends BaseActivity implements SetLocationView {

   private static final String TAG = SetLocationActivity.class.getSimpleName();

   private SetLocationPresenter mPresenter;

   private GoogleMap mMap;

   @Bind(R.id.address_field)
   public EditText mAddressField;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_set_location);

      mPresenter = new SetLocationPresenterImpl(this);
      initMaps();
   }

   OnMapReadyCallback mMapReadyCallback = new OnMapReadyCallback() {
      @Override
      public void onMapReady(GoogleMap googleMap) {
         mMap = googleMap;
         /**
          * Currently setting the default position to the Empire State Building.
          * Need to update the code to self-locate the phone (e.g. using LocationManager:
          * http://developer.android.com/guide/topics/location/strategies.html)
          */
         mMap.moveCamera(
               CameraUpdateFactory
                     .newLatLngZoom(new LatLng(40.748817, -73.985428), 14)
         );
      }
   };

   private void initMaps() {
      SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.location_map);
      mapFragment.getMapAsync(mMapReadyCallback);
   }

   @OnClick(R.id.search_address_button)
   public void lookForAddress() {
      String address = mAddressField.getText().toString();
      mPresenter.lookForAddress(address);
   }

   @OnClick(R.id.save_location_button)
   public void saveLocationButton() {
      if (mMap != null) {
         mPresenter.saveCurrentLocation(mMap.getCameraPosition().target);
      } else {
         Log.w(TAG, "Maps is not ready yet");
      }
   }

   @Override
   public void updateMapLocation(LatLng location) {
      mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
   }

   @Override
   public void goToNextStep() {
      Intent intent = new Intent(this, SetActivityWindowActivity.class);
      startActivity(intent);
      finish();
   }
}

