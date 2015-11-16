package com.locationlabs.porchlight.util.pubnub;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Enables PubNub when phone boots
 */
public class BootReceiver extends BroadcastReceiver {

   private static final String TAG = BootReceiver.class.getSimpleName();

   @Override
   public void onReceive(Context context, Intent arg1) {
      Log.i(TAG, "PubNub BootReceiver Starting");
      Intent intent = new Intent(context, PubNubService.class);
      context.startService(intent);
      Log.i(TAG, "PubNub BootReceiver Started");
   }

}
