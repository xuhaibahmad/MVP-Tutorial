package com.zuhaibahmad.template.usecases.main;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zuhaibahmad.template.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ContractMain.View {

	// @formatter:off
	@BindView(R.id.toolbar)				Toolbar 				mToolbar;
	@BindView(R.id.container)			CoordinatorLayout 		mContainer;
	// @formatter:on

	private ContractMain.Presenter mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		setSupportActionBar(mToolbar);

		mPresenter = new MainPresenter(this);
		mPresenter.start();
	}

	@OnClick(R.id.fab)
	public void onFloatingActionButtonClick(View view){
		mPresenter.onAction();
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
}
