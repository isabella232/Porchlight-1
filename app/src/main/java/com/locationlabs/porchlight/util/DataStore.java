package com.locationlabs.porchlight.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.locationlabs.porchlight.api.model.UserFlow;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Shared preference helper class
 */
public class DataStore {

   public static final String STORE_NAME = "llhack_preferences";

   protected static SharedPreferences sharedPref;

   protected static SharedPreferences getSharedPreferences(Context context) {
      if (sharedPref == null) {
         sharedPref = context.getSharedPreferences(STORE_NAME, Context.MODE_PRIVATE);
      }
      return sharedPref;
   }

   private static String getValue(Context context, String key) {
      SharedPreferences s = getSharedPreferences(context);
      return s.getString(key, null);
   }

   private static void setValue(Context context, String key, String value) {
      SharedPreferences s = getSharedPreferences(context);
      SharedPreferences.Editor e = s.edit();
      if (value == null) {
         e.remove(key);
      } else {
         e.putString(key, value);
      }
      e.apply();
   }

   private static boolean getBooleanValue(Context context, String key, boolean defaultValue) {
      String strVal = getValue(context, key);
      if (strVal == null) {
         return defaultValue;
      }
      try {
         return Boolean.parseBoolean(strVal);
      } catch (NumberFormatException nfe) {
         return defaultValue;
      }
   }

   private static void setBooleanValue(Context context, String key, boolean value) {
      setValue(context, key, Boolean.toString(value));
   }


   /**
    * DataStore keys
    */
   private static final String FIRST_RUN_KEY = "FIRST_RUN";
   private static final String CACHED_FLOWS_KEY = "CACHED_CHILDREN";
   private static final String NEW_FLOW_DATA_KEY = "NEW_FLOW_DATA";

   /**
    * DataStore Methods
    */
   public static void setFirstRun(Context context, boolean firstRun) {
      setBooleanValue(context, FIRST_RUN_KEY, firstRun);
   }

   public static boolean getFirstRun(Context context) {
      return getBooleanValue(context, FIRST_RUN_KEY, false);
   }

   public static void setCachedFlows(Context context, List<UserFlow> userFlowList) {
      Type listType = new TypeToken<List<UserFlow>>() {
      }.getType();
      String jsonifiedList = new Gson().toJson(userFlowList, listType);
      setValue(context, CACHED_FLOWS_KEY, jsonifiedList);
   }

   public static List<UserFlow> getCachedFlows(Context context) {
      String userFlowList = getValue(context, CACHED_FLOWS_KEY);
      Type listType = new TypeToken<List<UserFlow>>() {}.getType();
      List<UserFlow> cachedFlows = new Gson().fromJson(userFlowList, listType);
      if (cachedFlows == null) {
         cachedFlows = new ArrayList<>();
      }
      return cachedFlows;
   }

   public static void setNewFlowData(Context context, UserFlow newUserFlow) {
      String jsonifiedList = new Gson().toJson(newUserFlow, UserFlow.class);
      setValue(context, NEW_FLOW_DATA_KEY, jsonifiedList);
   }

   public static UserFlow getNewFlowData(Context context) {
      String userFlowList = getValue(context, NEW_FLOW_DATA_KEY);
      return new Gson().fromJson(userFlowList, UserFlow.class);
   }
}
