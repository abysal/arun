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
public class RegisterTest {
    @Rule
    public ActivityTestRule<Loginregister> register=new ActivityTestRule<>(Loginregister.class);
    @Before
    public void SetupFragment(){
        register.getActivity()
                .getFragmentManager().beginTransaction();
    }
    @Test
    public void loginFragment(){
        onView(withId(R.id.et_ufname)).perform(typeText("dd"));
        closeSoftKeyboard();

        onView(withId(R.id.et_ulname)).perform(typeText("dd"));
        closeSoftKeyboard();

        onView(withId(R.id.et_username)).perform(typeText("ee"));
        closeSoftKeyboard();

        onView(withId(R.id.et_password)).perform(typeText("ee"));
        closeSoftKeyboard();
        onView(withId(R.id.et_mail)).perform(typeText("ee"));
        closeSoftKeyboard();
        onView(withId(R.id.et_address)).perform(typeText("ee"));
        closeSoftKeyboard();
        onView(withId(R.id.btn_register2)).perform(click());
    }
}
