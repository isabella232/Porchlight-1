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
 * Mocked interactor, since backend is currently down
 */
public class SetFlowNameInteractorMockImpl extends BaseInteractorImpl implements SetFlowNameInteractor {

   SetFlowNamePresenter mPresenter;

   public SetFlowNameInteractorMockImpl(SetFlowNamePresenter presenter) {
      super(presenter);
      mPresenter = presenter;
   }

   @Override
   public UserFlow getCurrentNewFlow() {
      return DataStore.getNewFlowData(PorchlightApplication.getContext());
   }

   @Override
   public void saveCurrentFlow(UserFlow flow) {
      Context context = PorchlightApplication.getContext();

      // Mock
      List<UserFlow> flowList = DataStore.getCachedFlows(context);
      flowList.add(flow);
      DataStore.setCachedFlows(context, flowList);
      mPresenter.confirmDataSent(true);

      // Clear new flow
      DataStore.setNewFlowData(context, null);
   }

}
