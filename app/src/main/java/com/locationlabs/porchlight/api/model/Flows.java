package com.locationlabs.porchlight.api.model;

/**
 * List of Phone
 */
public class Flows {

   public UserFlow[] flows;

   public Flows(UserFlow[] flows) {
      this.flows = flows;
   }

   @Override
   public String toString() {
      String text = "";
      if (flows != null) {
         for (UserFlow flow : flows) {
            text += flow != null ? flow.toString() : "null";
         }
      }
      return text;
   }
}