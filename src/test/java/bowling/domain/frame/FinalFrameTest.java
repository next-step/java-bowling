package bowling.domain.frame;

import bowling.domain.score.TurnScore;
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

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class FinalFrameTest {
    private static FrameTest frameTest;

    @BeforeAll
    public static void setUp() {
        frameTest = new FrameTest();
    }

    @CsvSource({"0,10,10", "10,0,10"})
    @DisplayName("bowl 테스트 - 스페어 또는 스트라이크에만 허용 된다.")
    @ParameterizedTest
    void bowlTest(String scores) {
        assertThatCode(() -> toFinalFrameWithBowl(scores))
                .doesNotThrowAnyException();
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
        for (TurnScore iScore : frameTest.toFrameScores(strScores)) {
            frame = frame.bowl(iScore);
        }
        return frame;
    }
}