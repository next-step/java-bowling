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
 * create date  : 2019-07-10 16:20
 */
public class NormalFrameTest {
    @DisplayName("투구를 했을때 가능한지 확인, 2번이미 투구한사람은 새로운 Frame생성 알림을 위해 false로 반환")
    @Test
    void bowlCount() {
        NormalFrame normalFrame = NormalFrame.of(3);
        normalFrame.bowl(3);
        assertThat(normalFrame.bowl(3)).isFalse();
    }

    @DisplayName("투구를 했을때 가능한지 확인, 첫 투구가 Strike일 경우 false반환")
    @Test
    void bowlable() {
        NormalFrame normalFrame = NormalFrame.of(10);
        assertThat(normalFrame.bowl(1)).isFalse();
    }
}
