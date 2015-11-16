package com.locationlabs.porchlight.activity.main;

import android.content.Context;

import com.locationlabs.porchlight.PorchlightApplication;
import com.locationlabs.porchlight.api.model.ActivityWindow;
import com.locationlabs.porchlight.api.model.UserFlow;
import com.locationlabs.porchlight.util.DataStore;
import com.locationlabs.porchlight.util.base.BaseInteractorImpl;

import java.util.List;

/**
 * Mocked interactor, used for testing and for Github since backend is currently down
 */
public class MainInteractorMockImpl extends BaseInteractorImpl implements MainInteractor {

   private MainPresenter mPresenter;

   public MainInteractorMockImpl(MainPresenter presenter) {
      super(presenter);
      mPresenter = presenter;
   }

   @Override
   public void requestFlows() {
      Context context = PorchlightApplication.getContext();
      List<UserFlow> userFlowList = DataStore.getCachedFlows(context);

      /** Prepopulated mocked flows */
      if (userFlowList.isEmpty()) {
         userFlowList.add(new UserFlow("School", null, 0.0, 0.0,
                                       new ActivityWindow("", "8", "16"), true));
         userFlowList.add(new UserFlow("Night", "5105105100", 23.0, -12.0,
                                       new ActivityWindow("", "20", "8"), false));
         DataStore.setCachedFlows(context, userFlowList);
      }

      mPresenter.updateFlows(userFlowList);
   }

   @Override
   public void clearNewFlow() {
      DataStore.setNewFlowData(PorchlightApplication.getContext(), null);
   }

}
