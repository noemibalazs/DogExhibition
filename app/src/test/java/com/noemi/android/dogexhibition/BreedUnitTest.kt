package com.noemi.android.dogexhibition

import com.noemi.android.dogexhibition.api.model.Breed
import com.noemi.android.dogexhibition.api.model.BreedImages
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@org.robolectric.annotation.Config(manifest = Config.NONE)
class BreedUnitTest {

    private val breed = Breed(12, "vizsla")
    private val breedImage = BreedImages(12, "https://images.dog.ceo//breeds//buhund-norwegian//hakon1.jpg")

    @Test
    fun breedShouldPass() {
        val expected = Breed(12, "vizsla")
        assertEquals(expected, breed)
    }

    @Test
    fun breedShouldFailed() {
        val expected = Breed(12, "hungarian vizsla")
        assertNotEquals(expected, breed)
    }

    @Test
    fun breedImageShouldPass() {
        val expected = BreedImages(12, "https://images.dog.ceo//breeds//buhund-norwegian//hakon1.jpg")
        assertEquals(expected, breedImage)
    }

    @Test
    fun breedImageShouldFailed() {
        val expected = Breed(21, "https://images.dog.ceo//breeds//buhund-norwegian//hakon3.jpg")
        assertNotEquals(expected, breedImage)
    }
}