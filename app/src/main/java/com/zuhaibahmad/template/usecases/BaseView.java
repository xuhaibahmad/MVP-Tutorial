package com.zuhaibahmad.template.usecases;

/**
 * Created by Zuhaib Ahmad on 3/28/2017.
 * <p>
 * Base view for all presenters to inherit from
 */
public interface BaseView<T> {
	void setPresenter(T presenter);

	T getPresenter();
}
