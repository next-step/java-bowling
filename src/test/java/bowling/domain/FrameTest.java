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
 * create date  : 2019-07-13 15:10
 */
public class FrameTest {
    @DisplayName("현재 몇번째 Frame인지 확인")
    @Test
    void getIndex() {
        Frame frame1 = new Frame();
        Frame frame2 = new Frame();
        assertThat(frame2.getIndex()).isEqualTo(2);
    }
}
