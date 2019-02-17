package com.example.manu.cinemaappv2;

import android.content.Intent;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestDatesActivity {

    @Rule
    public ActivityTestRule<datesActivity> rule =
            new ActivityTestRule<>(datesActivity.class, true, false);

    @Before
    public void datos(){
        Intents.init();
    }
    @Test
    public void demostration() {
        // Context of the app under test.
        Intent intent =new Intent();


        rule.launchActivity(intent);

        String nameTest ="Manuel";
        onView(withId(R.id.editText_name)).perform(typeText(String.valueOf(nameTest)),
                closeSoftKeyboard());

        String lastnameTest ="Perez";
        onView(withId(R.id.editText_lastname)).perform(typeText(String.valueOf(lastnameTest)),
                closeSoftKeyboard());

        String addressTest ="Virgencito 19";
        onView(withId(R.id.editText_address)).perform(typeText(String.valueOf(addressTest)),
                closeSoftKeyboard());

        String mailTest ="ruanos@gmail.com";
        onView(withId(R.id.editText_mail)).perform(typeText(String.valueOf(mailTest)),
                closeSoftKeyboard());

        String phoneTest ="928421985";
        onView(withId(R.id.editText_phone)).perform(typeText(String.valueOf(phoneTest)),
                closeSoftKeyboard());

        //String genreTest ="Male";
        onView(withId(R.id.radioButton_male)).perform(click());

        onView(withId(R.id.fabDate)).perform(click());
        intended(hasComponent(MoredatesActivity.class.getName()));
    }

}
