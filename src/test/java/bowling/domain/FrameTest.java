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
    @DisplayName("현재 스코어의 합을 가지고 온다.")
    @Test
    void sum() {
        Frame frame = new Frame();
        frame.bowl(4);
        assertThat(frame.bowl(5)).isEqualTo(9);
    }

    @DisplayName("현재 스코어의 합을 가지고 온다.")
    @Test
    void getNumber() {
        Frame frame1 = new Frame();
        Frame frame2 = new Frame();
        Frame frame3 = new Frame();

        assertThat(frame3.getNumber()).isEqualTo(3);
    }

    @DisplayName("몇번 투구했는지 가지고 온다.")
    @Test
    void bowlCount() {
        Frame frame = new Frame();
        frame.bowl(3);
        frame.bowl(5);

        assertThat(frame.countBowl()).isEqualTo(2);
    }
}
