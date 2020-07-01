package bowling.domain.score;

import bowling.domain.pin.PinCount;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

public class ScoreTest {

    @DisplayName("생성 성공")
    @Test
    public void create() {
        assertThatCode(() -> Score.valueOf(10, 2))
                .doesNotThrowAnyException();
    }

    @DisplayName("각 상태값 점수 생성 성공")
    @ParameterizedTest
    @MethodSource
    public void scoreOfEachState(final Score score, final Score expected) {
        assertThat(score)
                .isEqualTo(expected);
    }

    private static Stream<Arguments> scoreOfEachState() {
        return Stream.of(
                Arguments.of(Score.ofStrike(), Score.valueOf(10, 2)),
                Arguments.of(Score.ofSpare(), Score.valueOf(10, 1)),
                Arguments.of(Score.ofMiss(8), Score.valueOf(8, 0))
        );
    }

    @DisplayName("현재 점수에 추가된 점수를 더하고 보너스 점수 개수를 하나 감소")
    @ParameterizedTest
    @MethodSource
    public void add(final Score score, final Score newScore, final Score expected) {
        assertThat(score.add(newScore))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> add() {
        return Stream.of(
                Arguments.of(Score.ofStrike(), Score.ofStrike(), Score.valueOf(20, 4)),
                Arguments.of(Score.ofStrike(), Score.valueOf(4, 0), Score.valueOf(14, 2)),
                Arguments.of(Score.ofStrike(), Score.ofMiss(8), Score.valueOf(18, 2)),
                Arguments.of(Score.ofStrike(), Score.ofSpare(), Score.valueOf(20, 3)),

                Arguments.of(Score.ofSpare(), Score.ofStrike(), Score.valueOf(20, 3)),
                Arguments.of(Score.ofSpare(), Score.valueOf(4, 0), Score.valueOf(14, 1)),
                Arguments.of(Score.ofSpare(), Score.ofMiss(8), Score.valueOf(18, 1)),
                Arguments.of(Score.ofSpare(), Score.ofSpare(), Score.valueOf(20, 2)),

                Arguments.of(Score.ofMiss(8), Score.ofStrike(), Score.valueOf(18, 2)),
                Arguments.of(Score.ofMiss(8), Score.valueOf(4, 0), Score.valueOf(12, 0)),
                Arguments.of(Score.ofMiss(8), Score.ofMiss(8), Score.valueOf(16, 0)),
                Arguments.of(Score.ofMiss(8), Score.ofSpare(), Score.valueOf(18, 1)),

                Arguments.of(Score.valueOf(4, 0), Score.valueOf(4, 0), Score.valueOf(8, 0)),
                Arguments.of(Score.valueOf(4, 0), Score.ofSpare(), Score.valueOf(14, 1))
        );
    }

    @DisplayName("현재 점수에 새로 추가된 점수만 추가하여 반환")
    @ParameterizedTest
    @MethodSource
    public void sum(final Score score, PinCount pinCount, final Score expected) {
        assertThat(score.sum(pinCount))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> sum() {
        PinCount maxCount = PinCount.of(PinCount.MAX_COUNT);
        PinCount fourCount = PinCount.of(4);
        PinCount eightCount = PinCount.of(8);

        return Stream.of(
                Arguments.of(Score.ofStrike(), maxCount, Score.valueOf(20, 1)),
                Arguments.of(Score.ofStrike(), fourCount, Score.valueOf(14, 1)),
                Arguments.of(Score.ofStrike(), eightCount, Score.valueOf(18, 1)),
                Arguments.of(Score.ofStrike(), maxCount, Score.valueOf(20, 1)),

                Arguments.of(Score.ofSpare(), maxCount, Score.valueOf(20, 0)),
                Arguments.of(Score.ofSpare(), fourCount, Score.valueOf(14, 0)),
                Arguments.of(Score.ofSpare(), eightCount, Score.valueOf(18, 0)),
                Arguments.of(Score.ofSpare(), maxCount, Score.valueOf(20, 0)),

                Arguments.of(Score.ofMiss(8), maxCount, Score.valueOf(18, -1)),
                Arguments.of(Score.ofMiss(8), fourCount, Score.valueOf(12, -1)),
                Arguments.of(Score.ofMiss(8), eightCount, Score.valueOf(16, -1)),
                Arguments.of(Score.ofMiss(8), maxCount, Score.valueOf(18, -1)),

                Arguments.of(Score.valueOf(4, 0), fourCount, Score.valueOf(8, -1)),
                Arguments.of(Score.valueOf(4, 0), maxCount, Score.valueOf(14, -1))
        );
    }

    @DisplayName("점수를 계산할 수 있는 상태인지 확인")
    @ParameterizedTest
    @MethodSource
    public void canCalculateScore(final Score score, final boolean expected) {
        assertThat(score.isCalculable())
                .isEqualTo(expected);
    }

    private static Stream<Arguments> canCalculateScore() {
        return Stream.of(
                Arguments.of(Score.ofMiss(8), true),
                Arguments.of(Score.valueOf(4, 0), true),
                Arguments.of(Score.UN_SCORE, false),
                Arguments.of(Score.ofStrike(), false),
                Arguments.of(Score.ofSpare(), false),
                Arguments.of(Score.UN_SCORE, false)
        );
    }

    @DisplayName("자신의 점수를 반환")
    @Test
    public void getScore() {
        assertThat(Score.valueOf(10, 0).getScore())
                .isEqualTo(10);
    }

    @DisplayName("여분의 보너스 개수가 0이 아니면 점수 반환 예외를 반환")
    @ParameterizedTest
    @MethodSource
    public void getScoreException(final Score score) {
        assertThatIllegalArgumentException()
                .isThrownBy(score::getScore);
    }

    private static Stream<Arguments> getScoreException() {
        return Stream.of(
                Arguments.of(Score.ofStrike()),
                Arguments.of(Score.ofSpare()),
                Arguments.of(Score.UN_SCORE)
        );
    }

    @DisplayName("자신의 점수를 반환")
    @Test
    public void equals() {
        assertThat(Score.valueOf(4, 0))
                .isEqualTo(Score.valueOf(4, 0));
    }
}
