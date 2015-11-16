package com.locationlabs.porchlight.activity.setflowname;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.locationlabs.porchlight.PorchlightApplication;
import com.locationlabs.porchlight.api.ApiResources;
import com.locationlabs.porchlight.api.model.ActivityWindow;
import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.DataStore;
import com.locationlabs.porchlight.util.VolleyUtil;
import com.locationlabs.porchlight.util.base.BaseInteractorImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Real interactor, not used anymore since backend is currently down
 */
public class SetFlowNameInteractorImpl extends BaseInteractorImpl implements SetFlowNameInteractor {

   private static final String TAG = SetFlowNameInteractorImpl.class.getSimpleName();

   private static final String REQUEST_TAG = "post_new_flow";

   SetFlowNamePresenter mPresenter;

   public SetFlowNameInteractorImpl(SetFlowNamePresenter presenter) {
      super(presenter);
      mPresenter = presenter;
   }

   @Override
   public UserFlow getCurrentNewFlow() {
      return DataStore.getNewFlowData(PorchlightApplication.getContext());
   }

   @Override
   public void saveCurrentFlow(UserFlow flow) {
      postNewFlow(flow);
   }

   private void postNewFlow(final UserFlow flow) {
      String url = ApiResources.getUrl(ApiResources.BASE_URL, ApiResources.FLOW_PATH);

      StringRequest stringRequest = new StringRequest(
            Request.Method.POST,
            url,
            new Response.Listener<String>() {
               @Override
               public void onResponse(String response) {
                  Log.d(TAG, "Received: " + response);

                  // Clear new flow
                  Context context = PorchlightApplication.getContext();
                  DataStore.setNewFlowData(context, null);

                  // Confirm data has been sent
                  mPresenter.confirmDataSent(true);
               }
            },
            new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                  mPresenter.confirmDataSent(false);
                  mPresenter.onError(error);
               }
            }) {
         @Override
         protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<>();
            params.put("activityWindow", new Gson().toJson(flow.getActivityWindow(), ActivityWindow.class));
            params.put("childMdn", flow.getChildMdn());
            params.put("enabled", String.valueOf(flow.getEnabled()));
            params.put("latitude", String.valueOf(flow.getLatitude()));
            params.put("longitude", String.valueOf(flow.getLongitude()));
            params.put("name", flow.getName());

            return params;
         }
         @Override
         public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            return headers;
         }

      };

      stringRequest.setTag(REQUEST_TAG);
      VolleyUtil
            .getInstance(PorchlightApplication.getContext())
            .addToRequestQueue(stringRequest);
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
