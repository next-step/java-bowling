package bowling.domain.frame;

import bowling.domain.score.InvalidScoreAddException;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("일반 프레임 테스트")
public class FrameTest {
    @DisplayName("일반 프레임 생성")
    @Test
    public void create() {
        Frame frame = Frame.first();

        assertThat(frame.getFrameNumber()).isEqualTo(1);
    }

    @DisplayName("다음 프레임 생성")
    @Test
    public void next() {
        Frame frame = Frame.first().next();

        assertThat(frame.getFrameNumber()).isEqualTo(2);
    }

    @DisplayName("점수 기록")
    @Test
    public void recordScore() {
        Frame frame = Frame.first();

        frame.record(10);

        assertThat(frame.getScores().get(0)).isEqualTo(Score.strike());
    }

    @DisplayName("점수기록하면 안되는 경우")
    @Test
    public void invalidRecordScore() {
        Frame frame = Frame.first();
        frame.record(10);

        assertThat(frame.isFinished()).isEqualTo(true);
        assertThatThrownBy(() -> {
            frame.record(10);
        }).isInstanceOf(InvalidScoreAddException.class);
    }

    @DisplayName("마지막 프레임 종료 확인 테스트")
    @ParameterizedTest
    @MethodSource("getScoresForLastFrame")
    public void lastFrame(List<Score> scores) {
        Frame frame = Frame.of(FrameNumber.last().getNumber(), scores);

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