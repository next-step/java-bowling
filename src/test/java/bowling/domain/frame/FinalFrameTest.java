package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScoreTest;
import bowling.exception.BowlFailureException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {
    @ValueSource(strings = {"0,10,10", "10,0,10", "4,5"})
    @DisplayName("bowl 테스트 - 스페어 또는 스트라이크에만 보너스 기회가 허용 된다.")
    @ParameterizedTest
    void bowlTest(String scores) {
        Frame frame = toFinalFrameWithBowl(scores);

        assertThat(frame.isCompleted())
                .isTrue();
    }

    @ValueSource(strings = {"4,5,10", "0,5,4", "10,0,10,10", "0,10,10,10"})
    @DisplayName("bowl 테스트 - 보너스 기회를 이미 사용 했거나 스페어 또는 스트라이크가 아니라면 BowlFailureException 발생.")
    @ParameterizedTest
    void bowlFailureTest_(String scores) {
        assertThatThrownBy(() -> toFinalFrameWithBowl(scores))
                .isInstanceOf(BowlFailureException.class);
    }

    private Frame toFinalFrameWithBowl(String strScores) {
        Frame frame = new FinalFrame();
        for (TurnScore iScore : TurnScoreTest.toFrameScores(strScores)) {
            frame = frame.bowl(iScore);
        }
        return frame;
    }
}