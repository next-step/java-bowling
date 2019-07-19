package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
    private FinalFrame frame;

    @BeforeEach
    void 초기화() {
        frame = new FinalFrame();
    }

    @DisplayName("FinalFrame - 두번 투구 게임종료")
    @Test
    void 프레임_생성_초기화() {
        frame.bowl(10);
        frame.bowl(10);
        frame.bowl(10);
        assertThat(frame.getState().printState()).isEqualTo("X|X|X");
//        assertAll(
//                () -> assertThat(frame.isGameOver()).isFalse();
//        );
    }
}
