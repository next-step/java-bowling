package bowling.domain.frame;

import bowling.domain.score.InvalidScoreAddException;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("마지막 프레임 테스트")
public class LastFrameTest {

    @DisplayName("마지막 프레임 종료 확인 테스트")
    @ParameterizedTest
    @MethodSource("getScoresForLastFrame")
    public void lastFrame(List<Score> scores) {
        Frame frame = LastFrame.of(FrameNumber.last().getNumber(), scores);

        assertThat(frame.isFinished()).isEqualTo(true);
        assertThatThrownBy(() -> {
            frame.record(10);
        }).isInstanceOf(InvalidScoreAddException.class);
    }

    private static Stream<Arguments> getScoresForLastFrame() {
        return Stream.of(Arguments.arguments(Arrays.asList(Score.strike(), Score.strike(), Score.strike())),
                Arguments.arguments(Arrays.asList(Score.strike(), Score.ordinary(1), Score.spare(9))),
                Arguments.arguments(Arrays.asList(Score.strike(), Score.ordinary(2), Score.ordinary(3))),
                Arguments.arguments(Arrays.asList(Score.ordinary(2), Score.spare(8), Score.ordinary(3))),
                Arguments.arguments(Arrays.asList(Score.ordinary(2), Score.spare(8), Score.strike())),
                Arguments.arguments(Arrays.asList(Score.ordinary(2), Score.spare(8), Score.gutter())),
                Arguments.arguments(Arrays.asList(Score.strike(), Score.gutter(), Score.gutter())),
                Arguments.arguments(Arrays.asList(Score.ordinary(2), Score.gutter())),
                Arguments.arguments(Arrays.asList(Score.gutter(), Score.gutter()))
        );
    }
}