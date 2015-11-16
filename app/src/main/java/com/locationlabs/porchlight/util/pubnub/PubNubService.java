package com.locationlabs.porchlight.util.pubnub;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Initializes PubNub when phone boots
 */
public class PubNubService extends Service {

   private static final String TAG = PubNubService.class.getSimpleName();

   PowerManager.WakeLock wl = null;

   public void onCreate() {
      super.onCreate();
      PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
      wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "SubscribeAtBoot");
      if (wl != null) {
         wl.acquire();
         Log.i(TAG, "Partial Wake Lock : " + wl.isHeld());
      }
      PubNubUtil.initialize();
   }

   @Override
   public void onDestroy() {
      super.onDestroy();
      if (wl != null) {
         wl.release();
         Log.i(TAG, "Partial Wake Lock : " + wl.isHeld());
         wl = null;
      }
   }

   @Nullable
   @Override
   public IBinder onBind(Intent intent) {
      return null;
   }

}
