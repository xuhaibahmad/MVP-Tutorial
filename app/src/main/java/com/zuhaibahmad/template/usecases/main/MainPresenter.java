package com.zuhaibahmad.template.usecases.main;

import android.content.Context;

import com.zuhaibahmad.template.R;

/**
 * Created by Zuhaib Ahmad on 3/31/2017.
 * <p>
 * Presenter for main activity
 */

class MainPresenter implements ContractMain.Presenter {

	private final ContractMain.View mView;
	private final Context mContext;

	MainPresenter(ContractMain.View view, Context context) {
		mView = view;
		mContext = context;
	}

	@Override
	public void start() {
		// Do your initialization work here
	}

	@Override
	public void performAddition(String numberOne, String numberTwo) {

		if (isEmptyInput(numberOne, numberTwo)) {
			// Display error message if any of the inputs is empty
			mView.displayMessage(mContext.getString(R.string.error_empty_input));
		} else {
			// Compute and pass the result to view for display
			final int firstNumber = Integer.parseInt(numberOne);
			final int secondNumber = Integer.parseInt(numberTwo);

			final int result = firstNumber + secondNumber;
			mView.displayResult(result);
		}
	}

	@Override
	public boolean isEmptyInput(String numOne, String numTwo) {
		return numOne == null || numOne.length() == 0 || numTwo == null || numTwo.length() == 0;
	}
}
