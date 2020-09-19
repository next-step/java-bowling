package bowling.model;

import bowling.ExceptionMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {

    @ParameterizedTest
    @ValueSource(ints = {-2, -1, -10})
    @DisplayName("Score 생성 실패 : 추가 점수 갯수가 0미만인 경우 ")
    void create_fail_min(int leftAddScoreCount) {
        assertThatThrownBy(() -> Score.of(10, leftAddScoreCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.SCORE_MIN_ADDITIONAL_COUNT_EXCEPTION);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 5, 10})
    @DisplayName("Score 생성 실패 : 추가 점수 갯수가 2초과인 경우 ")
    void create_fail_max(int leftAddScoreCount) {
        assertThatThrownBy(() -> Score.of(10, leftAddScoreCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.SCORE_MIN_ADDITIONAL_COUNT_EXCEPTION);
    }

    private static Stream<Arguments> provideForAddScore() {
        return Stream.of(
                Arguments.of(10, 1, 2, Score.of(12, 0)),
                Arguments.of(8, 2, 9, Score.of(17, 1))
        );
    }

    @ParameterizedTest
    @MethodSource("provideForAddScore")
    @DisplayName("점수 더하기")
    void addScore(int nowScore, int leftAddScoreCount, int addScore, Score expected) {
        // given
        Score result = Score.of(nowScore, leftAddScoreCount);

        // when
        result.addScore(addScore);

        // then
        assertThat(result).isEqualTo(expected);

    }
}
