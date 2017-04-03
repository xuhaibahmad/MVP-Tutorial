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

		void displayResult(int result);
	}

	interface Presenter extends BasePresenter {
		void performAddition(String numberOne, String numberTwo);

		boolean isEmptyInput(String numOne, String numTwo);
	}
}
