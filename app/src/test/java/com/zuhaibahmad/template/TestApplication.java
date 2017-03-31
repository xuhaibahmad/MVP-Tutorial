/*
 * Copyright (C) 2017 Zuhaib Ahmad
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.zuhaibahmad.template;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;

/**
 * Created by Zuhaib Ahmad on 1/20/2017.
 *
 * Application subclass to be used in Tests
 */

public class TestApplication extends MyApp {

    @Override
    public void onCreate() {
        super.onCreate();
        Configuration.Builder configuration = new Configuration.Builder(this).setDatabaseName(null);
        ActiveAndroid.initialize(configuration.create());           // Initialize ActiveAndroid DB
    }

    @Override
    public void onTerminate() {
        ActiveAndroid.dispose();
        super.onTerminate();
    }
}
