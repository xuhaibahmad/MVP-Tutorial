package com.zuhaibahmad.template;

import com.activeandroid.Model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Zuhaib Ahmad on 3/28/2017.
 * <p>
 * Constants used throughout application
 */

@SuppressWarnings({"WeakerAccess", "ArraysAsListWithZeroOrOneArgument", "unused"})
public final class Constants {

	// @formatter:off
	public static final String TAG 								= MyApp.class.getSimpleName();
	public static final String PREFERENCES_NAME					= TAG + ".prefs";
	public static final String LOG_FILE_NAME                    = TAG + "-Log.txt";
	public static final String DIRECTORY_NAME                   = TAG + " Log";

	// List of permissions required for this app
	public static final String[] PERMISSIONS = {
			// TODO Add application permissions
	};

	// List of ActiveAndroid models used
	public static final List<Model> MODELS = Arrays.asList(
			// TODO Add database models
	);

	public static final int REQUEST_PERMISSION_ALL				= 0;
	// @formatter:on

	private Constants() {
		// Exists only to defeat instantiation
	}
}
