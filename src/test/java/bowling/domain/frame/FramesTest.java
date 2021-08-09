package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScoreTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {
    @ValueSource(ints = {
            1, 10, 100
    })
    @ParameterizedTest
    void generateTest(int size) {
        assertThat(
                Frames.generate(size).size()
        ).isEqualTo(size);
    }

    @DisplayName("최소 1개의 프레임을 생성 해야 한다.")
    @ValueSource(ints = {
            0, -1
    })
    @ParameterizedTest
    void generateInvalidTest(int size) {
        assertThatThrownBy(() -> Frames.generate(size))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ValueSource(strings = {
            "0,0,0,10,10",
            "10,4",
            "5,4"
    })
    @DisplayName("bowl 테스트")
    @ParameterizedTest
    void bowlTest(String strScores) {
        List<TurnScore> scores = TurnScoreTest.toFrameScores(strScores);
        int framesSize = scores.size() / 2;

        Frames frames = Frames.generate(framesSize);

        scores.forEach(frames::bowl);

        assertThat(frames.isCompleted())
                .isTrue();
    }
}
