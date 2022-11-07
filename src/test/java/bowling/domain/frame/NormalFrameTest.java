package bowling.domain.frame;

import bowling.domain.status.Status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameTest {

    @Test
    @DisplayName("다음 프레임")
    void next_frame(Status state) {
        Frame frame = new NormalFrame(1);
        assertThat(frame.bowl()).isEqualTo(new NormalFrame(2));
    }

    @Test
    @DisplayName("마지막 프레임")
    void next_frame_final(Status state) {
        Frame frame = new NormalFrame(9);
        assertThat(frame.bowl()).isInstanceOf(FinalFrame.class);
    }


}