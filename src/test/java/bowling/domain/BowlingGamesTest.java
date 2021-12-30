package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGamesTest {

    @DisplayName("볼링 게임들 생성")
    @Test
    void create() {
        // when & then
        assertThat(BowlingGames.create(PlayersTest.PLAYERS_WITH_AYM_KMS)).isNotNull();
    }

    @DisplayName("볼링 게임들 반환")
    @Test
    void getBowlingGames() {
        // when
        BowlingGames bowlingGames = BowlingGames.create(PlayersTest.PLAYERS_WITH_AYM_KMS);
        // then
        assertThat(bowlingGames.getBowlingGames()).isEqualTo(Arrays.asList(BowlingGameTest.GAME_WITH_PLAYER_AYM, BowlingGameTest.GAME_WITH_PLAYER_KMS));
    }
}
