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
    @DisplayName("현재 몇번째 투구인지 확인")
    @Test
    void bowlCount() {
        NormalFrameScore normalScore = new NormalFrameScore();
        normalScore.bowl(3);
        normalScore.bowl(7);

        assertThat(normalScore.bowlCount()).isEqualTo(2);
    }
}
