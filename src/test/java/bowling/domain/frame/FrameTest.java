package bowling.domain.frame;

import bowling.domain.score.InvalidMaxScoresException;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

@DisplayName("일반 프레임 테스트")
public class FrameTest {
    Frame frame = Frame.empty();

    @DisplayName("점수 기록")
    @Test
    public void recordScore() {
        frame.record(10);

        assertThat(frame.getScores().get(0)).isEqualTo(Score.strike());
    }

    @DisplayName("점수기록하면 안되는 경우")
    @Test
    public void invalidRecordScore() {
        frame.record(3);
        frame.record(3);

        assertThat(frame.isFinished()).isEqualTo(true);
        assertThatThrownBy(() -> {
            frame.record(3);
        }).isInstanceOf(InvalidFrameRecordActionException.class);
    }

    @DisplayName("해당 프레임에서 더 점수를 추가할 수 있는지 확인")
    @ParameterizedTest
    @MethodSource("getScores")
    public void isFinished(List<Integer> scores, boolean isFinished) {
        record(scores);

        assertThat(frame.isFinished()).isEqualTo(isFinished);
    }

    private void record(List<Integer> scores) {
        for (int score : scores) {
            frame.record(score);
        }
    }

    private static Stream<Arguments> getScores() {
        return Stream.of(Arguments.arguments(Collections.emptyList(), false),
                Arguments.arguments(Collections.singletonList(9), false),
                Arguments.arguments(Collections.singletonList(0), false),
                Arguments.arguments(Arrays.asList(0, 0), true),
                Arguments.arguments(Arrays.asList(0, 1), true),
                Arguments.arguments(Arrays.asList(0, 10), true)
        );
    }

    @DisplayName("스페어 기록")
    @Test
    public void recordSpare() {
        record(Arrays.asList(2, 8));

        assertThat(frame.getScores().get(1)).isEqualTo(Score.spare(8));
    }

    @DisplayName("프레임에 점수가 없을 때 점수 계산")
    @Test
    public void calculateEmptyScore() {
        assertThat(frame.calculateScore(1, Arrays.asList(Frame.empty(), Frame.empty()))).isEqualTo(null);
    }

    @DisplayName("프레임이 끝나지 않았을 때 점수 계산")
    @Test
    public void calculateNotFinishedScore() {
        frame.record(3);
        assertThat(frame.calculateScore(10, Arrays.asList(Frame.empty(), Frame.empty()))).isEqualTo(null);
    }

    @DisplayName("일반 프레임 최대 점수 추가")
    @ParameterizedTest
    @ValueSource(ints = {8, 9, 10})
    public void normalFrameMaxScores(int score) {
        frame.record(3);
        assertThatThrownBy(() -> {
            frame.record(score);
        }).isInstanceOf(InvalidMaxScoresException.class);
    }
}