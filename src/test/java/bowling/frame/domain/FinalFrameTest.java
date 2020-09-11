package bowling.frame.domain;

import bowling.global.exception.OutOfFrameRangeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {

    private Frame finalFrame;

    @Test
    @DisplayName("마지막 Frame 생성")
    void create() {
        finalFrame = FinalFrame.finalFrame();
        assertThat(finalFrame.getNumber()).isEqualTo(10);
    }

}
