package com.zuhaibahmad.template;

import android.annotation.SuppressLint;
import android.support.v4.util.ArraySet;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.util.ActivityController;
import org.robolectric.util.ReflectionHelpers;

import java.util.ArrayList;

/**
 * Created by Zuhaib Ahmad on 3/28/2017.
 * <p>
 * Base test class for Robolectric tests
 */
@RunWith(RobolectricTestRunner.class)
@Config(
		constants = BuildConfig.class,
		application = TestApplication.class,
		sdk = {/*18, 19, 20, , 22, 23*/21}
)
public class BaseRobolectricTest<T extends AppCompatActivity> {

	private Class mClazz;
	private ActivityController<T> mActivityController;

	public BaseRobolectricTest(Class<T> clazz) {
		this.mClazz = clazz;
	}

	/**
	 * Get the activity. This method is not set inside the constructor but outside
	 * allow the tests control better when the activities will be set.
	 *
	 * @return The activity already visible
	 */
	@SuppressWarnings("unchecked")
	protected T getActivity() {
		this.mActivityController = Robolectric
				.buildActivity(mClazz).create().start().postCreate(null).resume().visible();
		return mActivityController.get();
	}

	@Before
	public void setUp() throws Exception {
		// Setup shadow stream to enable logging in Robolectric tests
		ShadowLog.stream = System.out;
	}

	@After
	public void tearDown() throws Exception {
		if (mActivityController != null) {
			mActivityController.pause().stop().destroy();
		}
	}

	@SuppressLint("NewApi")
	@After
	public void resetWindowManager() {
		Class clazz = ReflectionHelpers.loadClass(getClass().getClassLoader(), "android.view.WindowManagerGlobal");
		Object instance = ReflectionHelpers.callStaticMethod(clazz, "getInstance");

		// We essentially duplicate what's in {@link WindowManagerGlobal#closeAll} with what's below.
		// The closeAll method has a bit of a bug where it's iterating through the "roots" but
		// bases the number of objects to iterate through by the number of "views." This can result in
		// an {@link java.lang.IndexOutOfBoundsException} being thrown.
		Object lock = ReflectionHelpers.getField(instance, "mLock");

		ArrayList<Object> roots = ReflectionHelpers.getField(instance, "mRoots");
		//noinspection SynchronizationOnLocalVariableOrMethodParameter
		synchronized (lock) {
			for (int i = 0; i < roots.size(); i++) {
				ReflectionHelpers.callInstanceMethod(instance, "removeViewLocked",
						ReflectionHelpers.ClassParameter.from(int.class, i),
						ReflectionHelpers.ClassParameter.from(boolean.class, false));
			}
		}

		// Views will still be held by this array. We need to clear it out to ensure
		// everything is released.
		ArraySet<View> dyingViews = ReflectionHelpers.getField(instance, "mDyingViews");
		dyingViews.clear();
	}
}
