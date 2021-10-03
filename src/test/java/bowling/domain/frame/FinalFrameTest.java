package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import bowling.domain.state.running.Ready;
import bowling.exception.frame.FinalFrameCreateFrameException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameTest {

    @Test
    @DisplayName("FinalFrame은 다음 프레임을 생성할 수 없다.")
    void createNextFrameExceptionTest() {

        // given
        Frame frame = FinalFrame.of(new Ready());

        // when & then
        assertThatExceptionOfType(FinalFrameCreateFrameException.class)
            .isThrownBy(() -> frame.createNextFrame())
            .withMessageMatching("final frame은 다음 frame을 생성할 수 없습니다.");
    }
}