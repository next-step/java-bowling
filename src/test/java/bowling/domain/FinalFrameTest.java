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
 * create date  : 2019-07-11 00:31
 */
public class FinalFrameTest {
    @DisplayName("2번째 투구가 spare면 한번더 투구할 수 있다.")
    @Test
    void third_bowl() {
        FinalFrame finalFrame = FinalFrame.of(3);
        finalFrame.bowl(7);
        assertThat(finalFrame.bowl(10)).isTrue();
    }

    @DisplayName("1번째 투구가 strike면 한번더 투구할 수 있다.")
    @Test
    void first_strike_two_bowl() {
        FinalFrame finalFrame = FinalFrame.of(10);
        assertThat(finalFrame.bowl(10)).isTrue();
    }

    @DisplayName("1번째, 2번째 투구가 strike면 더이상 투구할 수 없다.")
    @Test
    void first_and_two_strike_third_bowl() {
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.bowl(10);
        assertThat(finalFrame.bowl(9)).isFalse();
    }

    @DisplayName("1번째, 2번째 투구까지 spare 점수 이상 되지 않으면 더이상 투구할 수 없다.")
    @Test
    void spare_score_under() {
        FinalFrame finalFrame = FinalFrame.of(1);
        finalFrame.bowl(1);
        assertThat(finalFrame.bowl(9)).isFalse();
    }
}
