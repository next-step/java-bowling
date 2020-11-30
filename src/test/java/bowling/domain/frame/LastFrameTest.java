package bowling.domain.frame;

import bowling.domain.pin.Pin;
import bowling.domain.score.InvalidMaxScoresException;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("마지막 프레임 테스트")
public class LastFrameTest {
    Frame frame = LastFrame.empty();

    @DisplayName("마지막 프레임 종료 확인 테스트")
    @ParameterizedTest
    @MethodSource("finished")
    public void lastFrame(List<Integer> scores) {
        record(scores);

        assertThat(frame.isFinished()).isEqualTo(true);
        assertThatThrownBy(() -> {
            frame.record(Pin.of(0));
        }).isInstanceOf(InvalidFrameRecordActionException.class);
    }

    private static Stream<Arguments> finished() {
        return Stream.of(Arguments.arguments(Arrays.asList(10, 10, 10)),
                Arguments.arguments(Arrays.asList(10, 1, 9)),
                Arguments.arguments(Arrays.asList(10, 2, 3)),
                Arguments.arguments(Arrays.asList(2, 8, 3)),
                Arguments.arguments(Arrays.asList(2, 8, 10)),
                Arguments.arguments(Arrays.asList(2, 8, 0)),
                Arguments.arguments(Arrays.asList(10, 0, 0)),
                Arguments.arguments(Arrays.asList(2, 0)),
                Arguments.arguments(Arrays.asList(0, 0))
        );
    }

    @DisplayName("스페어 가지는 프레임")
    @ParameterizedTest
    @MethodSource("spares")
    public void spareScore(List<Integer> scores, int spareIndex, Score expectedSpare) {
        record(scores);

        assertThat(frame.getScores().get(spareIndex)).isEqualTo(expectedSpare);
    }

    private static Stream<Arguments> spares() {
        return Stream.of(
                Arguments.arguments(Arrays.asList(2, 8), 1, Score.spare(8)),
                Arguments.arguments(Arrays.asList(10, 2, 8), 2, Score.spare(8))
        );
    }

    @DisplayName("마지막 프레임 진행중")
    @ParameterizedTest
    @MethodSource("notFinished")
    public void isFinishedAtLast(List<Integer> scores) {
        record(scores);

        assertThat(frame.isFinished()).isEqualTo(false);
    }

    private static Stream<Arguments> notFinished() {
        return Stream.of(
                Arguments.arguments(Collections.emptyList()),
                Arguments.arguments(Collections.singletonList(10)),
                Arguments.arguments(Collections.singletonList(9)),
                Arguments.arguments(Collections.singletonList(0)),
                Arguments.arguments(Arrays.asList(0, 10)),
                Arguments.arguments(Arrays.asList(10, 10))
        );
    }

    private void record(List<Integer> pins) {
        for (int pin : pins) {
            frame.record(Pin.of(pin));
        }
    }

    @DisplayName("마지막 프레임 점수 계산")
    @ParameterizedTest
    @MethodSource("getLastScoreParam")
    public void calculateStrikeScore(List<Integer> scores, Integer expectedScore) {
        record(scores);

        assertThat(frame.calculateScore(10, Collections.emptyList())).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> getLastScoreParam() {
        return Stream.of(
                Arguments.arguments(Collections.emptyList(), null),
                Arguments.arguments(Collections.singletonList(1), null),
                Arguments.arguments(Collections.singletonList(0), null),
                Arguments.arguments(Collections.singletonList(10), null),
                Arguments.arguments(Arrays.asList(0, 9), 19),
                Arguments.arguments(Arrays.asList(1, 4), 15),
                Arguments.arguments(Arrays.asList(1, 9), null),
                Arguments.arguments(Arrays.asList(1, 9, 3), 23),
                Arguments.arguments(Arrays.asList(10, 9, 1), 30),
                Arguments.arguments(Arrays.asList(10, 10, 10), 40)
        );
    }

    @DisplayName("마지막 프레임 최대 점수 추가")
    @ParameterizedTest
    @ValueSource(ints = {1, 9, 10})
    public void lastFrameMaxScores(int score) {
        record(Arrays.asList(10, 10, 10));

        assertThatThrownBy(() -> {
            frame.record(Pin.of(score));
        }).isInstanceOf(InvalidMaxScoresException.class);

    }
}