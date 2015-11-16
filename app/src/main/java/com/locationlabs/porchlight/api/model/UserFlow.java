package com.locationlabs.porchlight.api.model;

/**
 * Add short description here
 */
public class UserFlow {

   private String name;
   private String childMdn;
   private Double latitude;
   private Double longitude;
   private ActivityWindow activityWindow;
   private Boolean enabled;

   public UserFlow() {

   }

   public UserFlow(String name, String childMdn, Double latitude, Double longitude, ActivityWindow activityWindow, Boolean enabled) {
      this.name = name;
      this.childMdn = childMdn;
      this.latitude = latitude;
      this.longitude = longitude;
      this.activityWindow = activityWindow;
      this.enabled = enabled;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getChildMdn() {
      return childMdn;
   }

   public void setChildMdn(String childMdn) {
      this.childMdn = childMdn;
   }

   public Double getLatitude() {
      return latitude != null ? latitude : 0.0;
   }

   public void setLatitude(Double latitude) {
      this.latitude = latitude;
   }

   public Double getLongitude() {
      return longitude != null ? longitude : 0.0;
   }

   public void setLongitude(Double longitude) {
      this.longitude = longitude;
   }

   public ActivityWindow getActivityWindow() {
      return activityWindow;
   }

   public void setActivityWindow(ActivityWindow activityWindow) {
      this.activityWindow = activityWindow;
   }

   public boolean getEnabled() {
      return enabled != null && enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }
}
