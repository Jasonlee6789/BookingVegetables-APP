package com.example.quizapp;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class MainActivityTest {


    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);
    // this line is for testing the intent access to the second activity
    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor
            (LoginActivity.class.getName(),null,false);

            // create a instance of the target class
            MainActivity mainActivity;
           // String messageToDisply;
    @Before
    public void setUp() throws Exception {

        mainActivity = activityTestRule.getActivity();
        //messageToDisply ="This a  test message to test the input from the edit test"

    }
    @Test
    public void testLaunchLoginActivity(){
    assertNotNull(mainActivity.findViewById(R.id.btnLogin));
    onView(withId(R.id.btnLogin)).perform(click());
        Activity LoginActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(LoginActivity);

    }
    @After
    public void tearDown() throws Exception {
        mainActivity = null;
    }
}