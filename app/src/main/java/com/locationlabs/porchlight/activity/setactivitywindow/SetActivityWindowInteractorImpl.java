package com.locationlabs.porchlight.activity.setactivitywindow;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.locationlabs.porchlight.PorchlightApplication;
import com.locationlabs.porchlight.api.ApiResources;
import com.locationlabs.porchlight.api.model.ActivityWindow;
import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.DataStore;
import com.locationlabs.porchlight.util.VolleyUtil;
import com.locationlabs.porchlight.util.base.BaseInteractorImpl;

import java.util.HashMap;
import java.util.Map;


public class SetActivityWindowInteractorImpl extends BaseInteractorImpl
      implements SetActivityWindowInteractor {

   private SetActivityWindowPresenter mPresenter;

   public SetActivityWindowInteractorImpl(SetActivityWindowPresenter presenter) {
      super(presenter);
      mPresenter = presenter;
   }

   @Override
   public void saveActivityWindow(ActivityWindow activityWindow) {
      Context context = PorchlightApplication.getContext();
      UserFlow flow = DataStore.getNewFlowData(context);
      flow.setActivityWindow(activityWindow);
      DataStore.setNewFlowData(context, flow);
      mPresenter.confirmDataSent(true);
   }

}
