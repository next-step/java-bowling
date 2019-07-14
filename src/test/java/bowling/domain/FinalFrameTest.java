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
 * create date  : 2019-07-14 22:24
 */
public class FinalFrameTest {
    @DisplayName("게임이 종료되었는지 확인 - 종료")
    @Test
    void isGameOver() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(3);
        finalFrame.bowl(5);
        assertThat(finalFrame.isGameOver()).isTrue();
    }

    @DisplayName("게임이 종료되었는지 확인 - 첫 번째 투구 스트라이크로 인해 진행")
    @Test
    void isGameOver_false_by_firstStrike() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(10);
        finalFrame.bowl(5);
        assertThat(finalFrame.isGameOver()).isFalse();
    }

    @DisplayName("게임이 종료되었는지 확인 - 스페어로 인해 진행")
    @Test
    void isGameOver_false_by_spare() {
        FinalFrame finalFrame = new FinalFrame();
        finalFrame.bowl(5);
        finalFrame.bowl(5);
        assertThat(finalFrame.isGameOver()).isFalse();
    }
}
