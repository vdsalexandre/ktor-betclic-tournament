package com.tournament.domain.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tournament.bootstrap.KoinModule.koinModule
import com.tournament.domain.model.Player
import org.junit.jupiter.api.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import org.koin.test.junit5.AutoCloseKoinTest

class TournamentAdapterTest : AutoCloseKoinTest() {
    private val tournamentAdapter: TournamentAdapter by inject()

    init {
        startKoin {
            modules(koinModule)
        }
    }

    @Test
    fun `should return a list of playerDTO with the good ranking when a sorted list of one player is given`() {
        val players = listOf(Player(1, "marty.mcfly", 10))

        val playerDTOS = tournamentAdapter.setRanking(players)

        assertThat(playerDTOS[0].ranking).isEqualTo(1)
        assertThat(playerDTOS[0].nickname).isEqualTo("marty.mcfly")
    }

    @Test
    fun `should return a list of playerDTO with the good ranking when a sorted list of two players is given`() {
        val players = listOf(Player(1, "marty.mcfly", 15), Player(2, "emmet.brown", 10))

        val playerDTOS = tournamentAdapter.setRanking(players)

        assertThat(playerDTOS[0].ranking).isEqualTo(1)
        assertThat(playerDTOS[0].nickname).isEqualTo("marty.mcfly")

        assertThat(playerDTOS[1].ranking).isEqualTo(2)
        assertThat(playerDTOS[1].nickname).isEqualTo("emmet.brown")
    }

    @Test
    fun `should return a list of playerDTO with the good ranking when a sorted list of two players with same points is given`() {
        val players = listOf(Player(1, "marty.mcfly", 10), Player(2, "emmet.brown", 10))

        val playerDTOS = tournamentAdapter.setRanking(players)

        assertThat(playerDTOS[0].ranking).isEqualTo(1)
        assertThat(playerDTOS[0].nickname).isEqualTo("marty.mcfly")

        assertThat(playerDTOS[1].ranking).isEqualTo(1)
        assertThat(playerDTOS[1].nickname).isEqualTo("emmet.brown")
    }

    @Test
    fun `should return a list of playerDTO with the good ranking when a sorted list of three players with the first two having the same points is given`() {
        val players = listOf(Player(1, "marty.mcfly", 10), Player(2, "biff.tannen", 10), Player(3, "emmet.brown", 5))

        val playerDTOS = tournamentAdapter.setRanking(players)

        assertThat(playerDTOS[0].ranking).isEqualTo(1)
        assertThat(playerDTOS[0].nickname).isEqualTo("marty.mcfly")

        assertThat(playerDTOS[1].ranking).isEqualTo(1)
        assertThat(playerDTOS[1].nickname).isEqualTo("biff.tannen")

        assertThat(playerDTOS[2].ranking).isEqualTo(2)
        assertThat(playerDTOS[2].nickname).isEqualTo("emmet.brown")
    }

    @Test
    fun `should return a list of playerDTO with the good ranking when a sorted list of three players with the last two having the same points is given`() {
        val players = listOf(Player(1, "marty.mcfly", 10), Player(2, "biff.tannen", 5), Player(3, "emmet.brown", 5))

        val playerDTOS = tournamentAdapter.setRanking(players)

        assertThat(playerDTOS[0].ranking).isEqualTo(1)
        assertThat(playerDTOS[0].nickname).isEqualTo("marty.mcfly")

        assertThat(playerDTOS[1].ranking).isEqualTo(2)
        assertThat(playerDTOS[1].nickname).isEqualTo("biff.tannen")

        assertThat(playerDTOS[2].ranking).isEqualTo(2)
        assertThat(playerDTOS[2].nickname).isEqualTo("emmet.brown")
    }

    @Test
    fun `should return a list of playerDTO with the good ranking when a sorted list of ten players with same and different points is given`() {
        val players = listOf(
            Player(1, "marty.mcfly", 25),
            Player(2, "biff.tannen", 22),
            Player(3, "emmet.brown", 21),
            Player(4, "george.mcfly", 18),
            Player(5, "marvin.berry", 18),
            Player(6, "clara.clayton", 18),
            Player(7, "jennifer.parker", 15),
            Player(8, "mr.strickland", 14),
            Player(9, "lorraine.baines", 14),
            Player(10, "goldie.wilson", 13),
        )

        val playerDTOS = tournamentAdapter.setRanking(players)

        assertThat(playerDTOS[0].ranking).isEqualTo(1)
        assertThat(playerDTOS[1].ranking).isEqualTo(2)
        assertThat(playerDTOS[2].ranking).isEqualTo(3)
        assertThat(playerDTOS[3].ranking).isEqualTo(4)
        assertThat(playerDTOS[4].ranking).isEqualTo(4)
        assertThat(playerDTOS[5].ranking).isEqualTo(4)
        assertThat(playerDTOS[6].ranking).isEqualTo(5)
        assertThat(playerDTOS[7].ranking).isEqualTo(6)
        assertThat(playerDTOS[8].ranking).isEqualTo(6)
        assertThat(playerDTOS[9].ranking).isEqualTo(7)
    }
}