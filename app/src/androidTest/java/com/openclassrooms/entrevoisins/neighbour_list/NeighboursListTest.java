
package com.openclassrooms.entrevoisins.neighbour_list;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;
import com.openclassrooms.entrevoisins.utils.SelectViewAction;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.ViewPagerActions.scrollRight;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
    private static int ITEMS_COUNT = 12;

    private ListNeighbourActivity mActivity;

    private List<Neighbour> mNeighbourList = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least one item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        // Given : We remove the element at position 2
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(ViewMatchers.withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(ViewMatchers.withId(R.id.list_neighbours)).check(withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * When we click on an item, the item detailed page is opened
     */
    @Test
    public void myNeighbourItem_selectAction_shouldOpenDetailPage() {
        // Given : We click on one neighbour to get its detailed page
        onView(withId(R.id.list_neighbours))
                // When perform a click on one neighbour
                .perform(actionOnItemAtPosition(0, new SelectViewAction()));
        // Then : open its detailed page
        onView(withId(R.id.activity_details))
                .check(matches(isDisplayed()));
    }

    /**
     * We ensure that item personal name is shown in item detailed page
     */
    @Test
    public void myNeighbourDetailItem_openAction_shouldDisplayNeighbourName() {
        // Given : We click on one neighbour to get its detailed page
        onView(withId(R.id.list_neighbours))
        // When perform a click on one neighbour
                .perform(actionOnItemAtPosition(0, new SelectViewAction()));
        // Then : ensure that its displayed name matches its position
        onView(withId(R.id.neighbourName))
                .check(matches(withText(this.mNeighbourList.get(0).getName())));
    }

    /**
     * We ensure that only favorites items appear in favorite list
     */
    @Test
    public void myFavoritesNeighbours_swipeAction_ShouldShowOnlyFavoritesNeighbours() {
        // Given : Favorites list is not null (at least 1 item)
            onView(withId(R.id.list_neighbours))
                    .perform(actionOnItemAtPosition(0, new SelectViewAction()));
            onView(ViewMatchers.withId(R.id.favoritefab))
                    .perform((ViewAction) click());
            onView(withContentDescription("Revenir en haut de la page"))
                    .perform(click());
        // When: We swipe on favorite list tab
            onView(withId(R.id.container))
                    .perform(scrollRight());
        // Then : Show favorites neighbours only
            onView(withId(R.id.item_favlist_name))
                    .check(matches(withText(this.mNeighbourList.get(0).getName())));
   }
}