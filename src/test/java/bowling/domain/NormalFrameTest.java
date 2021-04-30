package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NormalFrameTest {
    private NormalFrame normalFrame;

    @BeforeEach
    void setUp() {
        normalFrame = new NormalFrame();
    }

    @Test
    @DisplayName("공 굴리기")
    void pitching_size() {
        normalFrame.pitching(1);

        assertThat(normalFrame.downPins.size())
                .isEqualTo(1);
        assertThat(normalFrame.states.size())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("다음 공 굴리기 가능 유무")
    void continue_pitching() {
        normalFrame.pitching(1);

        assertThat(normalFrame.isContinue())
                .isEqualTo(true);
    }

    @Test
    @DisplayName("턴 종료")
    void end_of_frame_turn() {
        normalFrame.pitching(10);

        assertThat(normalFrame.isEndFrame())
                .isEqualTo(true);
    }

    @Test
    @DisplayName("결과 유무")
    void frame_has_result() {
        normalFrame.pitching(1);

        assertAll(
                () -> assertFalse(new NormalFrame().hasResult()),
                () -> assertTrue(normalFrame.hasResult())
        );
    }
}
