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
        finalFrameScore.bowl(10);
        assertThat(finalFrameScore.bowl(10)).isTrue();
    }

    @DisplayName("스페어면 세번째 투구가 가능하다")
    @Test
    void third_bowl_spare() {
        FinalFrameScore finalFrameScore = new FinalFrameScore();
        finalFrameScore.bowl(5);
        finalFrameScore.bowl(5);
        assertThat(finalFrameScore.bowl(10)).isTrue();
    }

    @DisplayName("스트라이크면 세번째 투구가 가능하다")
    @Test
    void third_bowl_strike() {
        FinalFrameScore finalFrameScore = new FinalFrameScore();
        finalFrameScore.bowl(10);
        finalFrameScore.bowl(10);
        assertThat(finalFrameScore.bowl(10)).isTrue();
    }

    @DisplayName("4번째 투구는 던질 수 없다")
    @Test
    void third_bowl_strike_2() {
        FinalFrameScore finalFrameScore = new FinalFrameScore();
        finalFrameScore.bowl(10);
        finalFrameScore.bowl(10);
        finalFrameScore.bowl(10);
        assertThat(finalFrameScore.bowl(10)).isFalse();
    }
}
