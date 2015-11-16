package com.locationlabs.porchlight.activity.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.locationlabs.porchlight.PorchlightApplication;
import com.locationlabs.porchlight.R;
import com.locationlabs.porchlight.activity.handlecall.HandleCallActivity;
import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.pubnub.PubNubUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Add short description here
 */
public class FlowsAdapter extends RecyclerView.Adapter<FlowsAdapter.ViewHolder> {

   Context mContext;
   WeakReference<RecyclerView> mRecyclerViewRef;
   List<UserFlow> mUserFlowList;

   public FlowsAdapter(Context context, @NonNull List<UserFlow> flows) {
      mContext = context;
      mUserFlowList = flows;
   }

   @Override
   public void onAttachedToRecyclerView(RecyclerView recyclerView) {
      super.onAttachedToRecyclerView(recyclerView);
      mRecyclerViewRef = new WeakReference<>(recyclerView);
   }

   @Override
   public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
      super.onDetachedFromRecyclerView(recyclerView);
      if (mRecyclerViewRef != null) {
         mRecyclerViewRef.clear();
      }
   }

   protected static class ViewHolder extends RecyclerView.ViewHolder {
      public TextView mFlowName;
      public ImageView mContact;
      public ImageView mLocation;
      public ImageView mTime;
      public Switch mSwitch;

      public ViewHolder(View itemView) {
         super(itemView);
         mFlowName = (TextView) itemView.findViewById(R.id.flow_name);
         mContact = (ImageView) itemView.findViewById(R.id.contact_watchlist_icon);
         mLocation = (ImageView) itemView.findViewById(R.id.kid_location_icon);
         mTime = (ImageView) itemView.findViewById(R.id.activity_window_icon);
         mSwitch = (Switch) itemView.findViewById(R.id.flow_switch);
      }
   }

   public void updateFlows(@NonNull List<UserFlow> flows) {
      mUserFlowList = flows;
      if (mRecyclerViewRef != null && mRecyclerViewRef.get() != null) {
         notifyItemRangeInserted(0, mUserFlowList.size());
      }
   }

   @Override
   public FlowsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View itemView = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.list_user_flows, parent, false);
      return new FlowsAdapter.ViewHolder(itemView);
   }

   @Override
   public void onBindViewHolder(FlowsAdapter.ViewHolder holder, int position) {
      UserFlow flow = mUserFlowList.get(position);
      holder.mFlowName.setText(flow.getName());
      if (flow.getLatitude() == 0.0 || flow.getLongitude() == 0.0) {
         holder.mLocation.setVisibility(View.GONE);
      } else {
         holder.mLocation.setVisibility(View.VISIBLE);
      }

      if (flow.getChildMdn() != null && !flow.getChildMdn().isEmpty()) {
         holder.mContact.setVisibility(View.VISIBLE);
      } else {
         holder.mContact.setVisibility(View.GONE);
      }

      boolean isTimeRestrictionEnabled = flow.getActivityWindow() != null
            && flow.getActivityWindow().startTime != null
            && flow.getActivityWindow().endTime != null
            && !flow.getActivityWindow().startTime.equals(flow.getActivityWindow().endTime);
      if (isTimeRestrictionEnabled) {
         holder.mTime.setVisibility(View.VISIBLE);
      } else {
         holder.mTime.setVisibility(View.GONE);
      }

      holder.mSwitch.setChecked(flow.getEnabled());
      holder.mSwitch.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            if (PorchlightApplication.isMocked()) {
               // Mocked PubNub call
               // This is for the solely purpose of mocking the backend notification,
               // because backend is currently down
               PubNubUtil.handleMessage("", "");
            } else {
               // TODO: needs to tell the backend to monitor that flow
            }
         }
      });
   }

   @Override
   public int getItemCount() {
      return mUserFlowList != null ? mUserFlowList.size() : 0;
   }
}
