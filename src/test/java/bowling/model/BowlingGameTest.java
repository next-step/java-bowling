package bowling.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingGameTest {

    @DisplayName("게임에 참여하는 플레이어 등록에 성공한다")
    @Test
    void play_success() {
        // when
        BowlingGame bowlingGame = new BowlingGame();

        // then
        assertThat(bowlingGame).isNotNull();
    }

    @DisplayName("볼링을 친 결과를 반환한다")
    @Test
    void bowl_success() {
        // when
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(10))
                .play(Pins.valueOf(0)).play(Pins.valueOf(10))
                .play(Pins.valueOf(5)).play(Pins.valueOf(5))
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(1)).play(Pins.valueOf(9))
                .play(Pins.valueOf(1));

        // then
        assertThat(bowlingGame).isNotNull();
    }

    @DisplayName("볼링을 친 결과를 반환한다")
    @Test
    void bowl_success2() {
        // when
        BowlingGame bowlingGame = new BowlingGame();
        bowlingGame
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(10))
                .play(Pins.valueOf(0)).play(Pins.valueOf(10))
                .play(Pins.valueOf(5)).play(Pins.valueOf(5))
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(1)).play(Pins.valueOf(1))
                .play(Pins.valueOf(1)).play(Pins.valueOf(9))
                .play(Pins.valueOf(1));

        bowlingGame.print();

        // then
        assertThat(bowlingGame).isNotNull();
    }
}