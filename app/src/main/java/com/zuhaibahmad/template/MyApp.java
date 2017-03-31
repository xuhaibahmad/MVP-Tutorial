package com.zuhaibahmad.template;

import android.app.Activity;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.annimon.stream.Stream;
import com.squareup.leakcanary.LeakCanary;
import com.zuhaibahmad.template.utils.Utils;

import static android.support.v4.app.ActivityCompat.requestPermissions;

/**
 * Created by Zuhaib Ahmad on 3/28/2017.
 * <p>
 * Custom application class to handle global application state
 */

@SuppressWarnings("unchecked")
public class MyApp extends Application {

	static {
		AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		initLeakCanary();                                           // Initialize Leak Canary
		initActiveAndroid();                                        // Initialize ActiveAndroid DB
		setupGlobalExceptionHandler();                              // Global exception handling
	}

	/**
	 * For devices with Android version Marshmallow and above, user run-time permissions to check
	 * for required permissions and ask for those which are not granted
	 *
	 * @param activity The main activity of the application
	 */
	public static void checkPermissions(Activity activity) {
		// Check for permissions using Utility method and ask if condition does not pass
		if (!Utils.hasPermissions(activity, Constants.PERMISSIONS)) {
			requestPermissions(activity, Constants.PERMISSIONS, Constants.REQUEST_PERMISSION_ALL);
		}
	}

	/**
	 * Initializes the Leak Canary library
	 */
	private void initLeakCanary() {
		if (LeakCanary.isInAnalyzerProcess(this)) {
			// This process is dedicated to LeakCanary for heap analysis.
			// You should not init your app in this process.
			return;
		}
		LeakCanary.install(this);
	}

	/**
	 * Initializes the ActiveAndroid database
	 */
	private void initActiveAndroid() {
		Configuration.Builder config = new Configuration.Builder(this);
		Stream.of(Constants.MODELS).forEach(m -> config.addModelClasses(m.getClass()));
		ActiveAndroid.initialize(config.create());
	}

	/**
	 * Sets up a global exception handler that logs the stacktrace to a log file
	 */
	private void setupGlobalExceptionHandler() {
		// Set up global exception handler
		Thread.setDefaultUncaughtExceptionHandler((thread, e) -> {
			Utils.appendLog(Log.getStackTraceString(e), Constants.LOG_FILE_NAME);
			Log.e(Constants.TAG, "ERROR! " + e.toString(), e);
		});
	}

}
