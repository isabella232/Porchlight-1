package com.locationlabs.porchlight.api;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Add short description here
 */
public class ApiResources {

   /** URLs */
   @Retention(RetentionPolicy.SOURCE)
   @StringDef({BASE_URL})
   public @interface Url{}
   public static final String BASE_URL = "http://30b17501.ngrok.io"; // This doesn't work anymore

   /** Paths */
   @Retention(RetentionPolicy.SOURCE)
   @StringDef({PHONE_PATH, FLOW_PATH, ACTION_PATH})
   public @interface Path{}
   public static final String PHONE_PATH = "/phone";
   public static final String FLOW_PATH = "/flow";
   public static final String ACTION_PATH = "/action";

   public static String getUrl(@Url String url, @Path String path) {
      return url + path;
   }
}
