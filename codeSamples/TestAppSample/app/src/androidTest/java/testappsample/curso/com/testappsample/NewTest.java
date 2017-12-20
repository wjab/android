package testappsample.curso.com.testappsample;

import android.app.Application;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;

/**
 * Created by Administrador on 6/12/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class NewTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testLogInSuccess()
    {
        // appCompatButton
        ViewInteraction AppCompatEditText_User = onView(withId(R.id.user)).check(matches(isDisplayed()));
        ViewInteraction AppCompatEditText_Password = onView(withId(R.id.password)).check(matches(isDisplayed()));
        ViewInteraction appCompatButton_Login =onView(withId(R.id.login)).check(matches(isDisplayed()));

        AppCompatEditText_User.perform(replaceText("walter"), closeSoftKeyboard());
        AppCompatEditText_Password.perform(replaceText("admin"), closeSoftKeyboard());

        appCompatButton_Login.perform(click());

        onView(withId(R.id.login_message_success)).check(matches(isDisplayed()));
    }

    @Test
    public void testLogInFail()
    {
        // appCompatButton
        ViewInteraction AppCompatEditText_User = onView(withId(R.id.user)).check(matches(isDisplayed()));
        ViewInteraction AppCompatEditText_Password = onView(withId(R.id.password)).check(matches(isDisplayed()));
        ViewInteraction appCompatButton_Login =onView(withId(R.id.login)).check(matches(isDisplayed()));

        AppCompatEditText_User.perform(replaceText("myuser"), closeSoftKeyboard());
        AppCompatEditText_Password.perform(replaceText("mypassword"), closeSoftKeyboard());

        appCompatButton_Login.perform(click());

        ViewInteraction AppCompatTextView = onView(withId(R.id.fail)).check(matches(isDisplayed()));
        assertNotNull(AppCompatTextView);

        AppCompatTextView.check(matches(withText(containsString("Log in fail."))));
    }

    @After
    public void tearDown() throws Exception {
    }
}
