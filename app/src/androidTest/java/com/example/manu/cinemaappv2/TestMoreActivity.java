package com.example.manu.cinemaappv2;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
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
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestMoreActivity {

    @Rule
    public ActivityTestRule<MoredatesActivity> rule =
            new ActivityTestRule<>(MoredatesActivity.class, true, false);

    @Before
    public void datos(){
        Intents.init();
    }
    @Test
    public void demostration() {
        // Context of the app under test.
        Intent intent =new Intent();
        intent.putExtra("name","Pedro");
        intent.putExtra("lastname","Perez");
        intent.putExtra("address","Calle Virgencita 48");
        intent.putExtra("phone","928415520");
        intent.putExtra("mail","correo@prueba.es");
        intent.putExtra("genre","male");

        rule.launchActivity(intent);

        String countryText ="Spain";
        onView(withId(R.id.editText_country)).perform(typeText(countryText),
                closeSoftKeyboard());
        String cityText ="Las Palmas de Gran Canaria";
        onView(withId(R.id.editText_city)).perform(typeText(cityText),
                closeSoftKeyboard());
        String zipText ="35012";
        onView(withId(R.id.editText_zip)).perform(typeText(zipText),
                closeSoftKeyboard());

        onView(withId(R.id.fabMore)).perform(click());
        intended(hasComponent(MoremoreDatesActivity.class.getName()));
    }

}
