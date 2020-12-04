package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.NormalScores;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {

    @DisplayName("Frame Scores 반환 테스트")
    @ParameterizedTest
    @MethodSource("makeBowlResultData")
    void getResult(Score firstValue, Score secondValue) {
        Frame frame = NormalFrame.of(Frame.FIRST_FRAME, NormalScores.init());

        frame.bowl(firstValue);
        frame.bowl(secondValue);

        assertThat(frame.getResult()).containsExactly(firstValue, secondValue);
    }

    private static Stream<Arguments> makeBowlResultData() {
        return Stream.of(
                Arguments.of(Score.of("3"), Score.of("7")),
                Arguments.of(Score.of("0"), Score.of("3")),
                Arguments.of(Score.of("7"), Score.of("0"))
        );
    }

}
