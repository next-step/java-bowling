package frame;

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

    @ParameterizedTest
    @ValueSource(ints = {0, 11})
    void 프레임_번호_올바르지_않을경우(int number) {
        assertThatThrownBy(() -> new FrameNumber(number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(number + "는 올바르지 않은 프레임 번호입니다.");
    }

    @Test
    void 프레임다음번호_생성() {
        int number = 1;
        FrameNumber frameNumber = new FrameNumber(number);
        assertThat(frameNumber.next()).isEqualTo(2);
    }
}