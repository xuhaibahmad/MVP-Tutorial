package com.zuhaibahmad.template.usecases.main;

import com.zuhaibahmad.template.usecases.BasePresenter;
import com.zuhaibahmad.template.usecases.BaseView;

/**
 * Created by Zuhaib Ahmad on 3/31/2017.
 * <p>
 * Contract for main activity and its presenter
 */

public interface ContractMain {
	interface View extends BaseView<Presenter> {
		void displayMessage(String text);
	}

	interface Presenter extends BasePresenter {
		void onAction();
	}
}
