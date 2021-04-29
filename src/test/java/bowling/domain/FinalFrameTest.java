package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {
    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = new FinalFrame();
    }

    @Test
    @DisplayName(value = "공 굴리기")
    void pitching_size() {
        finalFrame.pitching(1);

        assertThat(finalFrame.downPins.size())
                .isEqualTo(1);
        assertThat(finalFrame.states.size())
                .isEqualTo(1);
    }

    @Test
    @DisplayName(value = "다음 공 굴리기 가능 유무")
    void continue_pitching() {
        finalFrame.pitching(1);

        assertThat(finalFrame.isContinue())
                .isEqualTo(true);
    }

    @Test
    @DisplayName(value = "턴 종료")
    void end_of_frame_turn() {
        finalFrame.pitching(10);
        finalFrame.pitching(10);
        finalFrame.pitching(10);

        assertThat(finalFrame.isEndFrame())
                .isEqualTo(true);
    }

    @Test
    @DisplayName(value = "결과 유무")
    void frame_has_result() {
        assertThat(finalFrame.hasResult())
                .isEqualTo(false);

        finalFrame.pitching(1);

        assertThat(finalFrame.hasResult())
                .isEqualTo(true);
    }
}