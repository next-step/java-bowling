package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
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
 * create date  : 2019-07-19 15:25
 */
public class FinalFrameTest {
    private Frame frame;

    @BeforeEach
    void 초기화() {
        frame = new NormalFrame();
        for (int i = 0; i <9 ; i++) {
            frame = frame.bowl(10);
        }
    }

    @DisplayName("FinalFrame 생성")
    @Test
    void 프레임_생성_초기화() {
        frame = frame.bowl(3);
        assertThat(frame.isGameOver()).isFalse();
    }
}
