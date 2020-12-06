package com.android_sample.flickr

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UiTest {

    @Test
    fun checkIfFullScreenImageIsDisplayed() {
        Intents.init()
        val activityScenario: ActivityScenario<ImageList> =
            ActivityScenario.launch(ImageList::class.java)
        activityScenario.moveToState(Lifecycle.State.RESUMED)

        // wait here for 10 seconds so that the data from the API can be loaded
        // this can fail if the data loading takes more than this time
        Thread.sleep(10000)

        Espresso.onView(ViewMatchers.withId(R.id.imageList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageListAdapter.Holder>(
                0,
                ViewActions.click()
            )
        )

        Intents.intended(IntentMatchers.hasComponent(FullScreenImage::class.java.name))

        Intents.release()
        activityScenario.moveToState(Lifecycle.State.DESTROYED)
    }

}