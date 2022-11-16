package bowling.domain;

import java.util.stream.IntStream;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BowlingGameTest {
    @Test
    @DisplayName("퍼펙트 게임 테스트")
    void testPerfectGame() {
        BowlingGame bowlingGame = new BowlingGame(null);
        IntStream.range(0, 12)
            .forEach(i -> bowlingGame.bowl(10));

        Assertions.assertThat(bowlingGame.isGamePlayable()).isFalse();
        Assertions.assertThat(bowlingGame.getCurrentFrameNumber()).isEqualTo(10);
    }

    @DisplayName("게임 도중 테스트")
    @ParameterizedTest(name = "진행중인 라운드: {0}")
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    void testNotFinishedGame(int round) {
        BowlingGame bowlingGame = new BowlingGame(null);
        IntStream.range(0, round)
            .forEach(i -> bowlingGame.bowl(10));

        Assertions.assertThat(bowlingGame.isGamePlayable()).isTrue();
        Assertions.assertThat(bowlingGame.getCurrentFrameNumber()).isEqualTo(round + 1);
    }
}
