package com.locationlabs.porchlight.util.base;


public class BaseInteractorImpl implements BaseInteractor {

   public BaseInteractorImpl(BasePresenter presenter) { }

   @Override
   public void cancelBackgroundTasks() {
      // To override when needed
   }
}