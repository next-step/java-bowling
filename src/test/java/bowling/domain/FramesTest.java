package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FramesTest {
    @DisplayName("Frames 생성")
    @Test
    void create() {
        Frames frames = new Frames();
        assertThat(frames).isEqualTo(new Frames());
    }

    @DisplayName("NormalFrame 생성")
    @ParameterizedTest
    @CsvSource(value = {"5,5,1", "10,10,2"})
    void pitch(int first, int second, int expect) {
        Frames frames = new Frames();
        frames.pitch(first);
        frames.pitch(second);

        assertThat(frames.getFrames()).hasSize(expect);
    }

    @DisplayName("FinalFrame 생성")
    @Test
    void pitch_final() {
        Frames frames = new Frames();
        for (int i = 0; i < 12; i++) {
            frames.pitch(10);
        }

        assertThat(frames.getFrames()).hasSize(10);
    }

    @DisplayName("점수 계산")
    @Test
    void getScore() {
        Frames frames = new Frames();
        frames.pitch(5);
        frames.pitch(2);
        frames.pitch(2);
        frames.pitch(2);

        assertThat(frames.getScores()).containsExactly(7, 4);
    }

    @DisplayName("퍼펙트 게임 점수 계산")
    @Test
    void getScore_perfectGame() {
        Frames frames = new Frames();
        for (int i = 0; i < 12; i++) {
            frames.pitch(10);
        }

        assertThat(frames.getScores()).containsExactly(30, 30, 30, 30, 30, 30, 30, 30, 30, 30);
    }
}
