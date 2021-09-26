package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.frame.FinalFrameCreateException;
import bowling.exception.frame.FinalFrameNextException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    @DisplayName("finalFrame이 다음 frame을 생성하고자 하면 Exception이 발생해야 한다.")
    void finalFrameCreateExceptionTest() {

        // given
        Frame frame = FinalFrame.of(10, null);

        // when & then
        assertThatExceptionOfType(FinalFrameCreateException.class)
            .isThrownBy(() -> frame.createNextFrame())
            .withMessageMatching("FinalFrame은 새로운 Frame을 생성할 수 없다.");
    }

    @Test
    @DisplayName("FinalFrame의 다음 frame을 찾고자하면 Exception이 반환되어야 한다.")
    void nextFrameTest() {

        // given
        Frame input = FinalFrame.of(10, null);

        // when & then
        assertThatExceptionOfType(FinalFrameNextException.class)
            .isThrownBy(() -> input.nextFrame())
            .withMessageMatching("FinalFrame은 다음 Frame을 확인할 수 없다.");
    }

}