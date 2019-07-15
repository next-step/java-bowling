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
        game.play(10);  // frame1
        game.play(1);   // frame2
        game.play(2);   // frame2
        game.play(1);   // frame3
        game.play(3);   // frame3
        game.play(1);   // frame4
        game.play(4);   // frame4
        game.play(1);   // frame5
        game.play(5);   // frame5
        game.play(1);   // frame6
        game.play(6);   // frame6
        game.play(1);   // frame7
        game.play(7);   // frame7
        game.play(1);   // frame8
        game.play(8);   // frame8
        game.play(1);   // frame9
        game.play(9);   // frame9
        assertThat(game.isNormalFrameOver()).isTrue();
    }
}
