package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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

    @DisplayName("투구가 가능한지 확인 - 투구가능횟수")
    @Test
    void isBowl_Count() {
        NormalFrameScore normalScore = new NormalFrameScore();
        normalScore.bowl(3);
        normalScore.bowl(7);

        assertThat(normalScore.isBowl()).isFalse();
    }

    @DisplayName("투구가 가능한지 확인 - Strike")
    @Test
    void isBowl_Strike() {
        NormalFrameScore normalScore = new NormalFrameScore();
        normalScore.bowl(10);

        assertThat(normalScore.isBowl()).isFalse();
    }

    @DisplayName("넘어간 Pin의 개수가 유효하지 않은상황 - 예외")
    @Test
    void invalid_downPin_count() {
        NormalFrameScore normalScore = new NormalFrameScore();
        assertThatIllegalArgumentException().isThrownBy(() -> {
            normalScore.bowl(11);
        }).withMessageContaining("쓰러진 핀의 개수가 유효하지 않습니다.");

    }
}
