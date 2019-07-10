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

    @DisplayName("FrameScore가 Over면 추가 X")
    @Test
    void addScore_false() {
        NormalFrameScore normalFrameScore = new NormalFrameScore(1);
        normalFrameScore.addScore(3);
        assertThat(normalFrameScore.addScore(10)).isFalse();
    }

    @DisplayName("FrameScore가 strike면 추가 X")
    @Test
    void addScore_false_by_Strike() {
        NormalFrameScore normalFrameScore = new NormalFrameScore(1);
        normalFrameScore.addScore(3);
        assertThat(normalFrameScore.addScore(10)).isFalse();
    }

    @DisplayName("현재 몇번 투구 했는지 확인")
    @Test
    void bowlCount() {
        NormalFrameScore normalFrameScore = new NormalFrameScore(1);
        assertThat(normalFrameScore.bowlCount()).isEqualTo(1);
    }
}
