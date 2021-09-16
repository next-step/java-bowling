package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.exception.frame.FinalFrameCreateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    @DisplayName("finalFrame이 다음 frame을 생성하고자 하면 Exception이 발생해야 한다.")
    void finalFrameCreateExceptionTest() {

        // given
        Frame frame = new FinalFrame(10, null);

        // when & then
        assertThatExceptionOfType(FinalFrameCreateException.class)
            .isThrownBy(() -> frame.createNextFrame())
            .withMessageMatching("FinalFrame은 새로운 Frame을 생성할 수 없다.");
    }

}