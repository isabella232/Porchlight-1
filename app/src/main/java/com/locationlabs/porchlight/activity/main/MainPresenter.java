package com.locationlabs.porchlight.activity.main;

import android.support.v7.widget.RecyclerView;

import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.base.BasePresenter;

import java.util.List;


public interface MainPresenter extends BasePresenter {

   RecyclerView.Adapter getFlowsAdapter();

   void updateFlows(List<UserFlow> flows);

   void clearNewFlow();

   void onStop();

   void onError(Exception e);

}
