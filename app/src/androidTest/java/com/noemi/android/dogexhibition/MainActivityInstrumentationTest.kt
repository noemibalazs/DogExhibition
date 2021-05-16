package com.noemi.android.dogexhibition

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.noemi.android.dogexhibition.api.model.Breed
import com.noemi.android.dogexhibition.landing.MainActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.noemi.android.dogexhibition.adapter.adapter.DogAdapter
import com.noemi.android.dogexhibition.adapter.adapter.DogClickListener
import com.noemi.android.dogexhibition.adapter.vh.DogVH

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentationTest {

    @get:Rule
    val rule = activityScenarioRule<MainActivity>()

    private val breedList = listOf(
        Breed(3, "husky"),
        Breed(6, "vizsla"),
        Breed(9, "collie")
    )

    @Test
    fun testAppContext() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals("com.noemi.android.dogexhibition.test", context.packageName)
    }

    @Test
    fun testBusinessLogic() {
        val dogClickListener: DogClickListener = { name, index ->
        }

        val adapter = DogAdapter(dogClickListener)
        adapter.submitList(breedList)

        rule.scenario.onActivity {
            it.findViewById<RecyclerView>(R.id.rvDogBreeds).adapter = adapter
        }

        onView(withId(R.id.rvDogBreeds)).check(matches(isDisplayed()))
        onView(withId(R.id.rvDogBreeds)).perform(
            RecyclerViewActions.actionOnItemAtPosition<DogVH>(
                0,
                ViewActions.click()
            )
        )
    }
}