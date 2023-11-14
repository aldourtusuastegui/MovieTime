package com.acsoft.movietime.feature_profile.domain.conversion

import com.acsoft.movietime.feature_profile.data.model.KnownForResponse
import com.acsoft.movietime.feature_profile.domain.entities.KnownFor
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ProfileConvertersTest {

    @Test
    fun `test convertKnownForListResponseToKnownForList with valid response`() {
        val knownForResponseList = listOf(
            KnownForResponse(
                id = 1,
                title = "Sheroes",
                backdropPath = "/qe0oK0A5ovrlgH39Ga13dxxw9MU.jpg",
                popularity = 160.0,
                overview = "When four thick-as-thieves friends arrive in Thailand, they quickly find themselves in over"
            ),
            KnownForResponse(
                id = 2,
                title = "Transformers: Rise of the Beasts",
                backdropPath = "/2vFuG6bWGyQUzYS9d69E5l85nIz.jpg",
                popularity = 190.0,
                overview = "When a new threat capable of destroying the entire planet emerges, Optimus Prime and the Autobots must team up with a powerful faction known as the Maximals. With the fate of humanity hanging in the balance"
            )
        )

        val result = ProfileConverters.convertKnownForListResponseToKnownForList(knownForResponseList)
        assertThat(result).containsExactly(
            KnownFor(
                id = 1,
                title = "Sheroes",
                backdropPath = "/qe0oK0A5ovrlgH39Ga13dxxw9MU.jpg",
                popularity = 160.0,
                overview = "When four thick-as-thieves friends arrive in Thailand, they quickly find themselves in over"
            ),
            KnownFor(
                id = 2,
                title = "Transformers: Rise of the Beasts",
                backdropPath = "/2vFuG6bWGyQUzYS9d69E5l85nIz.jpg",
                popularity = 190.0,
                overview = "When a new threat capable of destroying the entire planet emerges, Optimus Prime and the Autobots must team up with a powerful faction known as the Maximals. With the fate of humanity hanging in the balance"
            )
        ).inOrder()
    }

    @Test
    fun `test convertKnownForListResponseToKnownForList with null response`() {
        val knownForResponseList: List<KnownForResponse>? = null
        val result =
            ProfileConverters.convertKnownForListResponseToKnownForList(knownForResponseList)
        assertThat(result).isEmpty()
    }

    @Test
    fun `test convertKnownForListResponseToKnownForList with empty response`() {
        val knownForResponseList: List<KnownForResponse> = emptyList()
        val result =
            ProfileConverters.convertKnownForListResponseToKnownForList(knownForResponseList)
        assertThat(result).isEmpty()
    }
}