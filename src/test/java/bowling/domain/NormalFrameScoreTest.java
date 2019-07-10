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
 * create date  : 2019-07-10 16:10
 */
public class NormalFrameScoreTest {
    @DisplayName("FrameScore의 Size는 2인지 확인 가능합니다.")
    @Test
    void isOver() {
        FrameScore frameScore = new FrameScore();
        frameScore.addScore(1);
        frameScore.addScore(3);
        assertThat(frameScore.isExitFrame()).isTrue();
    }

    @DisplayName("FrameScore가 isOver가 아니면 추가")
    @Test
    void addScore_true() {
        FrameScore frameScore = new FrameScore();
        assertThat(frameScore.addScore(10)).isTrue();
    }

    @DisplayName("FrameScore가 Over면 추가 X")
    @Test
    void addScore_false() {
        FrameScore frameScore = new FrameScore();
        frameScore.addScore(1);
        frameScore.addScore(3);
        assertThat(frameScore.addScore(10)).isFalse();
    }

    @DisplayName("FrameScore가 strike면 추가 X")
    @Test
    void addScore_false_by_Strike() {
        FrameScore frameScore = new FrameScore();
        frameScore.addScore(1);
        frameScore.addScore(3);
        assertThat(frameScore.addScore(10)).isFalse();
    }

    @DisplayName("현재 몇번 투구 했는지 확인")
    @Test
    void bowlCount() {
        FrameScore frameScore = new FrameScore();
        assertThat(frameScore.bowlCount()).isEqualTo(0);
    }
}
