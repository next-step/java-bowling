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
 * create date  : 2019-07-13 22:30
 */
public class FinalFrameScoreTest {
    @DisplayName("한번 투구(스트라이크라도)가 되었으면 두번째 투구는 무조건 가능하다.")
    @Test
    void second_bowl() {
        FinalFrameScore finalFrameScore = new FinalFrameScore();
        finalFrameScore.addBowlScore(10);
        assertThat(finalFrameScore.addBowlScore(10)).isTrue();
    }

    @DisplayName("스페어면 세번째 투구가 가능하다")
    @Test
    void third_bowl_spare() {
        FinalFrameScore finalFrameScore = new FinalFrameScore();
        finalFrameScore.addBowlScore(5);
        finalFrameScore.addBowlScore(5);
        assertThat(finalFrameScore.addBowlScore(10)).isTrue();
    }

    @DisplayName("스트라이크면 세번째 투구가 가능하다")
    @Test
    void third_bowl_strike() {
        FinalFrameScore finalFrameScore = new FinalFrameScore();
        finalFrameScore.addBowlScore(10);
        finalFrameScore.addBowlScore(10);
        assertThat(finalFrameScore.addBowlScore(10)).isTrue();
    }

    @DisplayName("4번째 투구는 던질 수 없다")
    @Test
    void third_bowl_strike_2() {
        FinalFrameScore finalFrameScore = new FinalFrameScore();
        finalFrameScore.addBowlScore(10);
        finalFrameScore.addBowlScore(10);
        finalFrameScore.addBowlScore(10);
        assertThat(finalFrameScore.addBowlScore(10)).isFalse();
    }

    @DisplayName("첫 번째 투구가 스트라이크면 세번째 투구가 가능하다")
    @Test
    void third_bowl_strike_3() {
        FinalFrameScore finalFrameScore = new FinalFrameScore();
        finalFrameScore.addBowlScore(10);
        finalFrameScore.addBowlScore(5);
        assertThat(finalFrameScore.addBowlScore(3)).isTrue();
    }
}
