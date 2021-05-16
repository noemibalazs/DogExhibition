package com.noemi.android.dogexhibition

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.noemi.android.dogexhibition.adapter.adapter.DogImageAdapter
import com.noemi.android.dogexhibition.adapter.vh.DogVH
import com.noemi.android.dogexhibition.api.model.BreedImages
import com.noemi.android.dogexhibition.searching.RandomDogExhibitionActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed

@RunWith(AndroidJUnit4ClassRunner::class)
class RandomActivityInstrumentationTest {

    @get:Rule
    val rule = activityScenarioRule<RandomDogExhibitionActivity>()

    private val randomImages = listOf(
        BreedImages(12, "https://images.dog.ceo//breeds//collie-border//Jake.jpg"),
        BreedImages(21, "https://images.dog.ceo//breeds//collie-border//Zoe.jpg"),
        BreedImages(24, "https://images.dog.ceo//breeds//collie-border//n02106166_1055.jpg"),
        BreedImages(27, "https://images.dog.ceo//breeds//collie-border//n02106166_1059.jpg")
    )

    @Test
    fun testBusinessLogic() {

        val adapter = DogImageAdapter()
        adapter.submitList(randomImages)

        rule.scenario.onActivity {
            it.findViewById<RecyclerView>(R.id.rvDogExhibition).adapter = adapter
        }

        onView(withId(R.id.rvDogExhibition))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rvDogExhibition)).perform(
            RecyclerViewActions.actionOnItemAtPosition<DogVH>(
                0,
                ViewActions.click()
            )
        )
    }
}