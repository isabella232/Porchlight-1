package com.locationlabs.porchlight.activity.handlecall;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.locationlabs.porchlight.api.routecall.CallRouting;
import com.locationlabs.porchlight.util.base.BasePresenterImpl;

/**
 * Presenter for handling call routing
 */
public class HandleCallPresenterImpl extends BasePresenterImpl implements HandleCallPresenter {

   // TODO: Set a default phone number in case user doesn't grant permission
   private static final String DEFAULT_PHONE_NUMBER = "";
   private static final String EMPTY_MDN = "";

   private HandleCallView mView;
   private HandleCallInteractor mInteractor;

   private String mPhoneNumber;

   public HandleCallPresenterImpl(HandleCallView view) {
      super(view);
      mView = view;
      mInteractor = new HandleCallInteractorImpl(this);

      getDevicePhoneNumber();
   }

   /**
    * Get the device phone number if the permission READ_PHONE_STATE is granted.
    * NOTE: Starting from Android M, need to check if the permission is granted or not.
    */
   private void getDevicePhoneNumber() {
      // TODO: perform a permission check/request here for M devices
      if (true) {
         TelephonyManager tMgr =
               (TelephonyManager) mView.getContext().getSystemService(Context.TELEPHONY_SERVICE);
         mPhoneNumber = tMgr.getLine1Number();
      } else {
         mPhoneNumber = DEFAULT_PHONE_NUMBER;
      }
   }

   @Override
   public void handleAllowCall() {
      mInteractor.handleCall(CallRouting.ACTION_CONTINUE, EMPTY_MDN);
      mView.mFinish();
   }

   @Override
   public void handleBlockCall() {
      mInteractor.handleCall(CallRouting.ACTION_ENDCALL, EMPTY_MDN);
      mView.mFinish();
   }

   @Override
   public void handleAnswerCall() {
      mInteractor.handleCall(CallRouting.ACTION_ROUTE, mPhoneNumber);
      mView.mFinish();
   }
}
