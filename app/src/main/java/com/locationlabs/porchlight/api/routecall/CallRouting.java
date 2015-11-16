package com.locationlabs.porchlight.api.routecall;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Add short description here
 */
public class CallRouting {

   /** Actions */
   @Retention(RetentionPolicy.SOURCE)
   @StringDef({ACTION_ROUTE, ACTION_CONTINUE, ACTION_ENDCALL})
   public @interface Action{}
   public static final String ACTION_ROUTE = "Route";
   public static final String ACTION_CONTINUE = "Continue";
   public static final String ACTION_ENDCALL = "EndCall";
}
