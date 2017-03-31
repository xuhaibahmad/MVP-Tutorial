package com.zuhaibahmad.template.usecases.main;

import com.zuhaibahmad.template.MyApp;
import com.zuhaibahmad.template.R;

/**
 * Created by Zuhaib Ahmad on 3/31/2017.
 * <p>
 * Presenter for main activity
 */

public class MainPresenter implements ContractMain.Presenter {

	private final ContractMain.View mView;
	private final MainActivity mActivity;
	private final MyApp mApplication;

	public MainPresenter(ContractMain.View view) {
		mView = view;
		mActivity = (MainActivity) mView;

		mApplication = (MyApp) mActivity.getApplicationContext();
	}

	@Override
	public void start() {
		MyApp.checkPermissions(mActivity);
	}

	@Override
	public void onAction() {
		mView.displayMessage(mActivity.getString(R.string.placeholder_action_message));
	}

}
