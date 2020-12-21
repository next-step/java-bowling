package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    @Test
    @DisplayName("스트라이크일 때, FinalFrame 생성")
    void strike() {
        Frame finalFrame = FinalFrame.init();

        Pitch pitch = Pitch.from(10);
        finalFrame.add(pitch);
        assertThat(finalFrame.getPitchSize()).isEqualTo(1);
    }

}
