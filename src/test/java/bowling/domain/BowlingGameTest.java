package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-14 23:13
 */
public class BowlingGameTest {
    @DisplayName("NormalFrame이 종료되었는지 확인하기")
    @Test
    void checkFrameNumber() {
        List<Integer> downCount = new ArrayList<>(Arrays.asList(
                10, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9
        ));
        BowlingGame game = new BowlingGame();
        for (int down : downCount) {
            game.play(down);
        }
        assertThat(game.isNormalFrameOver()).isTrue();
    }

    @DisplayName("전체 게임 종료 확인 - FinalFrame 3번 투구")
    @Test
    void gameOver() {
        List<Integer> downCount = new ArrayList<>(Arrays.asList(
                10, 1, 2, 1, 3, 1, 4, 1, 5, 1, 6, 1, 7, 1, 8, 1, 9, 5, 5, 10, 1
        ));
        BowlingGame game = new BowlingGame();

        assertThatExceptionOfType(IllegalStateException.class).isThrownBy(() -> {
            for (int down : downCount) {
                game.play(down);
            }
        }).withMessageContaining("게임 종료");
    }
}
