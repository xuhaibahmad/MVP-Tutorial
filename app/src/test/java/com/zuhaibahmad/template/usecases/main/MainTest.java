package com.zuhaibahmad.template.usecases.main;

import android.content.Context;

import com.zuhaibahmad.template.R;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Zuhaib Ahmad on 4/3/2017.
 * <p>
 * Tests for main screen
 */

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

	private static final String EMPTY_INPUT_ERROR_MESSAGE = "Please Provide Both Numbers!";

	@Mock
	private ContractMain.View mView;
	@Mock
	private Context mContext;

	private ContractMain.Presenter mPresenter;

	@Before
	public void setUp() throws Exception {
		mPresenter = new MainPresenter(mView, mContext);
	}

	@Test
	public void performAddition_shouldReturnResult() throws Exception {
		final String numberOne = String.valueOf(2);
		final String numberTwo = String.valueOf(3);

		mPresenter.performAddition(numberOne, numberTwo);

		verify(mView).displayResult(5);
	}

	@Test
	public void performAddition_shouldDisplayErrorMessage() throws Exception {
		final String numberOne = String.valueOf(2);
		final String numberTwo = null;
		// Mock error string because our mock context wouldn't be able to return it
		when(mContext.getString(R.string.error_empty_input)).thenReturn(EMPTY_INPUT_ERROR_MESSAGE);

		mPresenter.performAddition(numberOne, numberTwo);

		verify(mView).displayMessage(EMPTY_INPUT_ERROR_MESSAGE);
	}
}
