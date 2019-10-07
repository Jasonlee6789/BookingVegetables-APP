package com.example.quizapp;

import android.app.Activity;
import android.app.Instrumentation;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    // this line is for testing the intent access to the second activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor
            (ListActivity.class.getName(),null,false);

    // create a instance of the target class
    LoginActivity loginActivity;

    @Before
    public void setUp() throws Exception {

        loginActivity = activityTestRule.getActivity();
    }

    // this is UnitTest for testing the access to the ListActivity
    @Test
    public void testLaunchListActivity(){
        assertNotNull(loginActivity.findViewById(R.id.btnGo));
        onView(withId(R.id.btnGo)).perform(click());

        Activity ListActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(ListActivity);
    }

    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }
}