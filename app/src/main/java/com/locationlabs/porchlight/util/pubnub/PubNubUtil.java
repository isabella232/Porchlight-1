package com.locationlabs.porchlight.util.pubnub;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.locationlabs.porchlight.PorchlightApplication;
import com.locationlabs.porchlight.R;
import com.locationlabs.porchlight.activity.handlecall.HandleCallActivity;
import com.pubnub.api.Callback;
import com.pubnub.api.Pubnub;
import com.pubnub.api.PubnubError;
import com.pubnub.api.PubnubException;


public class PubNubUtil {

   private static final String TAG = PubNubUtil.class.getSimpleName();

   // TODO: you need to get a pubnub account or it won't work
   private static final String PUBNUB_PUBLISHER_KEY = "demo";
   private static final String PUBNUB_SUBSCRIBER_KEY = "demo";

   private static final String PUBNUB_CHANNEL = "demo_porchlight";

   private static Pubnub mPubNub;

   private static Callback mPubNubSubscriptionCallback = new Callback() {
      @Override
      public void successCallback(String channel, Object message) {
         handleMessage(channel, message);
      }

      @Override
      public void errorCallback(String channel, PubnubError error) {
         Log.e(TAG, "Error subscribing to channel " + channel + ": " + error);
      }

      @Override
      public void connectCallback(String channel, Object message) {
         Log.d(TAG, "Connecting to channel " + channel);
      }

      @Override
      public void reconnectCallback(String channel, Object message) {
         Log.d(TAG, "Reconnecting to channel " + channel);
      }

      @Override
      public void disconnectCallback(String channel, Object message) {
         Log.d(TAG, "Disconnecting from channel " + channel);
      }
   };

   /**
    * Initialize a PubNub subscription
    */
   public static void initialize() {
      if (mPubNub == null) {
         mPubNub = new Pubnub(PUBNUB_PUBLISHER_KEY, PUBNUB_SUBSCRIBER_KEY);
      }

      try {
         Log.d(TAG, "Initiating subscription to channel " + PUBNUB_CHANNEL);
         mPubNub.subscribe(PUBNUB_CHANNEL, mPubNubSubscriptionCallback);
      } catch (PubnubException e) {
         Log.e(TAG, "Cannot subscribe to PubNub: " + e);
      }
   }

   public static void handleMessage(String channel, Object message) {
      String textMessage = message != null ? message.toString() : "null";
      Log.d(TAG, channel + " - Received: " + textMessage);

      Context context = PorchlightApplication.getContext();

      Intent intent = new Intent(context, HandleCallActivity.class);
      PendingIntent pendingIntent =
            PendingIntent.getActivity(context, 102, intent, PendingIntent.FLAG_ONE_SHOT);

      NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
      builder
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle("Somebody is calling your kid")
            .setContentText("Click here to handle the call yourself")
            .setAutoCancel(false)
            .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_app_small_icon);

      NotificationManager notificationManager =
            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
      notificationManager.notify(101, builder.build());
   }
}
