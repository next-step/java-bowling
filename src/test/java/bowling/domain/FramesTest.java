package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FramesTest {
    private Frames frames;

    @BeforeEach
    void setUp() {
        frames = new Frames();
    }

    @Test
    @DisplayName("프레임 일급 컬렉션 생성")
    void create_frames() {
        assertThat(frames.frames()
                .size())
                .isEqualTo(10);
    }

    @ParameterizedTest
    @DisplayName("다음 라운드 숫자")
    @CsvSource(value = {"1:0", "10:1"}, delimiter = ':')
    void frames_next_round(int input, int result) {
        frames.rollingBowl(input);

        frames.adjustRound();

        assertThat(frames.currentRound())
                .isEqualTo(result);
    }
}
