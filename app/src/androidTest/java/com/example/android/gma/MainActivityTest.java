package com.example.android.gma;

import android.provider.Telephony;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.*;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void clickLoginButton_SuccessfulLogin() throws Exception {
        onView(withId(R.id.username_input)).perform(typeText("SUB.Sabarish"));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.password_input)).perform(typeText("sabaris"), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        TimeUnit.SECONDS.sleep(45);
        onView(withId(R.id.hola_text)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }
        @After
    public void tearDown() throws Exception {
    }
}