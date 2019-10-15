package com.example.quizapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.widget.EditText;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
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

    String nameToDisply;
    String telToDisply;



    @Before
    public void setUp() throws Exception {

        loginActivity = activityTestRule.getActivity();

        nameToDisply ="test LJ";
        telToDisply = "666";

    }

    // this is UnitTest for testing the access to the ListActivity
    @Test
    public void testLaunchListActivity(){
        assertNotNull(loginActivity.findViewById(R.id.btnGo));
        onView(withId(R.id.btnGo)).perform(click());

        Activity ListActivity = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(ListActivity);
    }


    // this @Test  is to test the name + tel Edittext from LoginActivity to ListActivity
   @Test
    public void testUserInputTelScenario() {
       onView(withId(R.id.et_name )).perform(typeText(nameToDisply));
        onView(withId(R.id.et_tel )).perform(typeText(telToDisply));

        closeSoftKeyboard();

        onView(withId(R.id.btnGo)).perform(click());

        onView(withId(R.id.textUser)).check(matches(withText("name:" + nameToDisply + "   TEL:"+ telToDisply)));
    }
    @After
    public void tearDown() throws Exception {
        loginActivity = null;
    }
}