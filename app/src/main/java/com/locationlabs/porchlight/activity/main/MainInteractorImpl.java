package com.locationlabs.porchlight.activity.main;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.locationlabs.porchlight.PorchlightApplication;
import com.locationlabs.porchlight.api.ApiResources;
import com.locationlabs.porchlight.api.model.Flows;
import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.DataStore;
import com.locationlabs.porchlight.util.VolleyUtil;
import com.locationlabs.porchlight.util.base.BaseInteractorImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Real interactor implementation doing API calls.
 * Backend is currently down so using mock instead for Github.
 */
public class MainInteractorImpl extends BaseInteractorImpl implements MainInteractor {

   private static final String TAG = MainInteractorImpl.class.getSimpleName();

   private static final String REQUEST_TAG = "REQUEST_TAG";

   private MainPresenter mPresenter;

   public MainInteractorImpl(MainPresenter presenter) {
      super(presenter);
      mPresenter = presenter;
   }

   @Override
   public void requestFlows() {
      String url = ApiResources.getUrl(ApiResources.BASE_URL, ApiResources.FLOW_PATH);
      StringRequest stringRequest = new StringRequest(
            Request.Method.GET, url,
            new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                  Log.d(TAG, "Received: " + response);

                  List<UserFlow> flows = new ArrayList<>();
                  if (response != null) {
                     Flows flowList = new Gson().fromJson(response, Flows.class);
                     flows.addAll(Arrays.asList(flowList.flows));
                  }

                  mPresenter.updateFlows(flows);
               }
            },
            new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                  mPresenter.onError(error);
               }
            });

      stringRequest.setTag(REQUEST_TAG);
      VolleyUtil
            .getInstance(PorchlightApplication.getContext())
            .addToRequestQueue(stringRequest);
   }

   @Override
   public void clearNewFlow() {
      DataStore.setNewFlowData(PorchlightApplication.getContext(), null);
   }

   @Override
   public void cancelBackgroundTasks() {
      super.cancelBackgroundTasks();
      VolleyUtil
            .getInstance(PorchlightApplication.getContext())
            .getRequestQueue()
            .cancelAll(REQUEST_TAG);
   }
}

