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
 * create date  : 2019-07-13 13:56
 */
public class NormalFrameScoreTest {
    @DisplayName("투구의 합을 가지고 온다")
    @Test
    void sum() {
        NormalFrameScore normalFrameScore = new NormalFrameScore();
        normalFrameScore.addBowlScore(3);
        normalFrameScore.addBowlScore(6);
        assertThat(normalFrameScore.sum()).isEqualTo(9);
    }

    @DisplayName("예외상황 - 투구 시 기존 스코어가 이미 10점을 넘었을 경우")
    @Test
    void exception_score() {
        NormalFrameScore normalFrameScore = new NormalFrameScore();
        normalFrameScore.addBowlScore(10);
        assertThat(normalFrameScore.addBowlScore(1)).isFalse();
    }

    @DisplayName("예외상황 - 투구 시 이미 2번의 투구를 끝냈을 경우")
    @Test
    void exception_bowl_count() {
        NormalFrameScore normalFrameScore = new NormalFrameScore();
        normalFrameScore.addBowlScore(1);
        normalFrameScore.addBowlScore(1);
        assertThat(normalFrameScore.addBowlScore(1)).isFalse();
    }

    @DisplayName("NormalFrame단위 최대 얻을 수 있는 score가 넘을 경우")
    @Test
    void invalidScore() {
        NormalFrameScore normalFrameScore = new NormalFrameScore();
        normalFrameScore.addBowlScore(1);
        assertThat(normalFrameScore.invalidScore(10)).isTrue();
    }

    @DisplayName("NormalFrame단위 2번 이상 투구하는지 확인하는 테스트")
    @Test
    void invalidBowlCount() {
        NormalFrameScore normalFrameScore = new NormalFrameScore();
        normalFrameScore.addBowlScore(1);
        normalFrameScore.addBowlScore(1);
        assertThat(normalFrameScore.invalidBowlCount()).isTrue();
    }

    @DisplayName("스트라이크 체크")
    @Test
    void isStrike() {
        NormalFrameScore normalFrameScore = new NormalFrameScore();
        normalFrameScore.addBowlScore(10);
        assertThat(normalFrameScore.isStrike()).isTrue();
    }

    @DisplayName("스페어 체크")
    @Test
    void isSpare() {
        NormalFrameScore normalFrameScore = new NormalFrameScore();
        normalFrameScore.addBowlScore(1);
        normalFrameScore.addBowlScore(9);
        assertThat(normalFrameScore.isSpare()).isTrue();
    }
}
