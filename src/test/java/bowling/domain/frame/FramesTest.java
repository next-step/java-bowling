package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScoreTest;
import bowling.exception.FrameEmptyException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FramesTest {
    @CsvSource(value = {
            "0,0,0,10,10|4",
            "10,4|2",
            "5,4|2",
            "5|1"
    }, delimiter = '|')
    @DisplayName("currentFrameNumber 테스트")
    @ParameterizedTest
    void bowlTest(String strScores, int correctCurrentFrameNumber) {
        Frames frames = toFrames(strScores);

        assertThat(frames.currentFrameNumber())
                .isEqualTo(correctCurrentFrameNumber);
    }

    @ValueSource(strings = {
            "10,10,10,10,10,10,10,10,10,4,5",   // 마지막 일반
            "10,10,10,10,10,10,10,10,10,10,5",  // 마지막 스트라이크 - 보너스
            "10,10,10,10,10,10,10,10,10,4,6,5",  // 마지막 스페어 - 보너스
    })
    @DisplayName("bowl 테스트")
    @ParameterizedTest
    void bowlTest(String strScores) {
        assertThat(toFrames(strScores).isCompleted())
                .isTrue();
    }

    private Frames toFrames(String strScores) {
        List<TurnScore> scores = TurnScoreTest.toFrameScores(strScores);

        Frames frames = new Frames();
        scores.forEach(frames::bowl);

        return frames;
    }
}
