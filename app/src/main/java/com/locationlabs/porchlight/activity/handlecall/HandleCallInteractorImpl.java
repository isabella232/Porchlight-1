package com.locationlabs.porchlight.activity.handlecall;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.locationlabs.porchlight.PorchlightApplication;
import com.locationlabs.porchlight.api.ApiResources;
import com.locationlabs.porchlight.api.routecall.CallRouting;
import com.locationlabs.porchlight.util.VolleyUtil;
import com.locationlabs.porchlight.util.base.BaseInteractorImpl;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Interactor handling calls routing
 */
public class HandleCallInteractorImpl extends BaseInteractorImpl implements HandleCallInteractor {

   private final static String TAG = HandleCallInteractorImpl.class.getSimpleName();

   private static final String REQUEST_TAG = "REQUEST_TAG_HANDLE_CALL";

   private HandleCallPresenter mPresenter;

   public HandleCallInteractorImpl(HandleCallPresenter presenter) {
      super(presenter);
      mPresenter = presenter;
   }

   @Override
   public void handleCall(@CallRouting.Action final String action, final String routeAddress) {
      String url = ApiResources.getUrl(ApiResources.BASE_URL, ApiResources.ACTION_PATH);
      JSONObject object = new JSONObject();
      try {
         object.put("action", action);
         object.put("routeAddress", routeAddress);
         Log.d(TAG, "Going to send: " + object.toString());
      } catch (JSONException e) {
         e.printStackTrace();
      }
      JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.POST, url, object,
            new Response.Listener<JSONObject>() {
               @Override
               public void onResponse(JSONObject response) {
                  Log.d(TAG, "Received: " + response);
               }
            },
            new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError error) {
                  mPresenter.onError(error);
               }
            }) {
         @Override
         public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            return headers;
         }

      };

      jsonObjectRequest.setTag(REQUEST_TAG);
      VolleyUtil
            .getInstance(PorchlightApplication.getContext())
            .addToRequestQueue(jsonObjectRequest);
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
