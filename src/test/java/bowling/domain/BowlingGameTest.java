package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        BowlingGame game = new BowlingGame();
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        game.play(10);
        assertThat(game.isNormalFrameOver()).isTrue();
    }
}
