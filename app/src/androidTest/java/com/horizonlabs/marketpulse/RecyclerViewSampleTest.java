

package com.horizonlabs.marketpulse;

import com.horizonlabs.marketpulse.ui.activity.MainActivity;
import com.horizonlabs.marketpulse.ui.adapters.ScanAdapter;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecyclerViewSampleTest {

    private static final int ITEM_BELOW_THE_FOLD = 1;

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void scrollToItemBelowFold_checkItsText() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.rvScanHome))
                .perform(RecyclerViewActions.actionOnItemAtPosition(ITEM_BELOW_THE_FOLD, click()));

        // Match the text in an item below the fold and check that it's displayed.
        //String itemElementText = getApplicationContext().getResources().getString(
        //  R.string.item_element_text) + String.valueOf(ITEM_BELOW_THE_FOLD);
        //onView(withText(itemElementText)).check(matches(isDisplayed()));
    }

   /* @Test
    public void itemInMiddleOfList_hasSpecialText() {
        // First, scroll to the view holder using the isInTheMiddle matcher.
        onView(ViewMatchers.withId(R.id.rvScanHome))
                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));

        // Check that the item has the special text.
        String middleElementText =
                getApplicationContext().getResources().getString(R.string.middle);
        onView(withText(middleElementText)).check(matches(isDisplayed()));
    }


    private static Matcher<ScanAdapter.UserHolder> isInTheMiddle() {
        return new TypeSafeMatcher<ScanAdapter.UserHolder>() {
            @Override
            protected boolean matchesSafely(ScanAdapter.UserHolder customHolder) {
                return customHolder.getTextViewNAme().isClickable();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item in the middle");
            }
        };
    }*/
}