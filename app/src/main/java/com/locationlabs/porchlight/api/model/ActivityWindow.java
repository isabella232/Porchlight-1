package com.locationlabs.porchlight.api.model;

/**
 * ActivityWindow model object. MDN is currently unused, but would be a better way to link
 * users to an ActivityWindow
 */
public class ActivityWindow {

   public String mdn;
   public String startTime;
   public String endTime;

   public ActivityWindow(String mdn, String startTime, String endTime) {
      this.mdn = mdn;
      this.startTime = startTime;
      this.endTime = endTime;
   }
}
