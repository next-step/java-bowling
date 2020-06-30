package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("BowlingGames 클래스 테스트")
class BowlingGamesTest {
    final List<String> playerNames = Arrays.asList("ksj", "psj");

    @Test
    void create() {
        BowlingGames bowlingGames = new BowlingGames(new Players(playerNames));

        List<BowlingGame> actual = bowlingGames.getBowlingGames();

        assertThat(actual).hasSize(2);
    }

    @Test
    void isEndGame_false() {
        BowlingGames bowlingGames = new BowlingGames(new Players(playerNames));

        boolean actual = bowlingGames.isEndGame();

        assertThat(actual).isFalse();
    }

    @Test
    void isEndGame_true() {
        BowlingGames bowlingGames = new BowlingGames(new Players(Collections.singletonList("ksj")));

        while(!bowlingGames.isEndGame()) {
            BowlingGame currentGame = bowlingGames.getCurrentGame();
            currentGame.play(new Pin(10));
        }

        boolean actual = bowlingGames.isEndGame();

        assertThat(actual).isTrue();
    }

    @Test
    void getCurrentGame() {
        BowlingGames bowlingGames = new BowlingGames(new Players(playerNames));

        BowlingGame currentGame = bowlingGames.getCurrentGame();

        assertThat(currentGame.getCurrentFrameNumber()).isEqualTo(1);
        assertThat(currentGame.getPlayerName()).isEqualTo("ksj");
    }

    @Test
    void getCurrentGame_exception() {
        BowlingGames bowlingGames = new BowlingGames(new Players(playerNames));

        while(!bowlingGames.isEndGame()) {
            BowlingGame currentGame = bowlingGames.getCurrentGame();
            currentGame.play(new Pin(10));
        }

        assertThatExceptionOfType(CannotGetCurrentGameException.class)
                .isThrownBy(bowlingGames::getCurrentGame)
                .withMessage(CannotGetCurrentGameException.MESSAGE);
    }
}
