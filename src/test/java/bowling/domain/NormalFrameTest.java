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
 * create date  : 2019-07-13 17:41
 */
public class NormalFrameTest {
    @DisplayName("투구가 가능한 상태인지 체크 (스트라이크)")
    @Test
    void invalid_bowl_by_strike() {
        NormalFrame normal = new NormalFrame();
        normal.bowl(10);
        assertThat(normal.bowl(10)).isEqualTo(normal);
    }

    @DisplayName("현재 몇번째 Frame인지 확인")
    @Test
    void getIndex() {
        NormalFrame frame1 = new NormalFrame();
        NormalFrame frame2 = new NormalFrame();
        assertThat(frame2.getIndex()).isEqualTo(2);
    }
}
