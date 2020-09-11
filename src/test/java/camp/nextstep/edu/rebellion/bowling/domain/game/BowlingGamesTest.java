package camp.nextstep.edu.rebellion.bowling.domain.game;

import camp.nextstep.edu.rebellion.bowling.domain.player.GamePlayers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingGamesTest {
    @DisplayName("볼링 게임 참가자 순으로 점수가 잘 기록되는지 확인")
    @Test
    public void bowlingGamesTest() {
        // given
        int strike = 10;
        String firstPlayer = "ABC";
        String secondPlayer = "DEF";

        GamePlayers gamePlayers = GamePlayers.join(Arrays.asList(firstPlayer, secondPlayer));
        BowlingGames bowlingGames = BowlingGames.start(gamePlayers);

        // when & then
        assertAll(
                () -> assertThat(bowlingGames.hasNext()).isTrue(),
                () -> assertThat(bowlingGames.currentPlayerName()).isEqualTo(firstPlayer)
        );

        // and
        bowlingGames.record(strike);

        // then
        assertAll(
                () -> assertThat(bowlingGames.hasNext()).isTrue(),
                () -> assertThat(bowlingGames.currentPlayerName()).isEqualTo(secondPlayer)
        );

        // and
        bowlingGames.record(9);

        // then
        assertAll(
                () -> assertThat(bowlingGames.hasNext()).isTrue(),
                () -> assertThat(bowlingGames.currentPlayerName()).isEqualTo(secondPlayer)
        );

        // and
        bowlingGames.record(1);

        // then
        assertAll(
                () -> assertThat(bowlingGames.hasNext()).isTrue(),
                () -> assertThat(bowlingGames.currentPlayerName()).isEqualTo(firstPlayer)
        );
    }
}
