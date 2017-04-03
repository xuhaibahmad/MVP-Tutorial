package com.zuhaibahmad.template.usecases.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import com.zuhaibahmad.template.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ContractMain.View {

	// @formatter:off
	@BindView(R.id.toolbar)				Toolbar 				mToolbar;
	@BindView(R.id.container)			CoordinatorLayout 		mContainer;
	@BindView(R.id.numberOneEditText) 	EditText 				mNumberOneEditText;
	@BindView(R.id.numberTwoEditText) 	EditText 				mNumberTwoEditText;
	@BindView(R.id.resultTextView)		TextView 				mResultTextView;
	// @formatter:on

	private ContractMain.Presenter mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		setSupportActionBar(mToolbar);

		mPresenter = new MainPresenter(this, this);
		mPresenter.start();
	}

	@Override
	public void displayMessage(String text) {
		Snackbar.make(mContainer, text, Snackbar.LENGTH_LONG).show();
	}

	@Override
	public void setPresenter(ContractMain.Presenter presenter) {
		mPresenter = presenter;
	}

	@Override
	public ContractMain.Presenter getPresenter() {
		return mPresenter;
	}

	@OnClick(R.id.addButton)
	public void onAddButtonClicked() {
		final String numberOne = mNumberOneEditText.getText().toString();
		final String numberTwo = mNumberTwoEditText.getText().toString();

		mPresenter.performAddition(numberOne, numberTwo);
	}

	@Override
	public void displayResult(int result) {
		mResultTextView.setText(String.valueOf(result));
	}
}
