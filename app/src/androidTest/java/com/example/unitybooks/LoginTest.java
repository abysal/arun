package com.example.unitybooks;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.example.unitybooks.Users.Loginregister;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest

public class LoginTest {

    @Rule
    public ActivityTestRule<Loginregister> login=new ActivityTestRule<>(Loginregister.class);

    @Before
    public void SetupFragment(){
        login.getActivity()
                .getFragmentManager().beginTransaction();
    }

    @Test
    public void loginFragment(){
        onView(withId(R.id.et_Username)).perform(typeText("ss"));
        closeSoftKeyboard();

        onView(withId(R.id.et_lpassword)).perform(typeText("ss"));
        closeSoftKeyboard();

        onView(withId(R.id.btn_lsignin)).perform(click());
    }
}
