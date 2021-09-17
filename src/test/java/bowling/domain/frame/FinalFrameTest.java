package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.frame.FinalFrameCreateException;
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
    @DisplayName("FinalFrame의 다음 frame은 null이 반환되어야 한대")
    void nextFrameTest() {


        // given
        Frame input = FinalFrame.of(10, null);

        // when
        Frame result = input.nextFrame();

        // then
        assertThat(result).isNull();
    }

}