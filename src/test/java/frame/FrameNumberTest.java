package frame;

import frame.info.FrameNumber;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FrameNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    void 프레임_번호생성(int number) {
        FrameNumber frameNumber = new FrameNumber(number);

        assertThat(frameNumber).isEqualTo(new FrameNumber(number));
    }

    @Test
    void 프레임_번호_올바르지_않을경우() {
        assertThatThrownBy(() -> new FrameNumber(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(0 + "는 올바르지 않은 프레임 번호입니다.");
    }

    @Test
    void 프레임다음번호_생성() {
        int number = 1;
        FrameNumber frameNumber = new FrameNumber(number);
        assertThat(frameNumber.next()).isEqualTo(2);
    }
}