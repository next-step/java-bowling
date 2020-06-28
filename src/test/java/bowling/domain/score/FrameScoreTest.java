package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class FrameScoreTest {
    @ParameterizedTest
    @MethodSource("parametersByCreateFrameScore")
    @DisplayName("FrameScore 점수의 두 합은 10 점을 초과할 수 없다.")
    void validate_FrameScore(Score firstScore, Score secondScore) {
        assertThatThrownBy(() -> FrameScore.of(firstScore, secondScore))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한 프레임 당 점수의 합은 " + FrameScore.MAX_SCORE_PER_FRAME + "점을 초과할 수 없습니다.");
    }

    static Stream<Arguments> parametersByCreateFrameScore() {
        return Stream.of(
                arguments(Score.of(10), Score.of(10)),
                arguments(Score.of(1), Score.of(10)),
                arguments(Score.of(5), Score.of(6)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 9})
    @DisplayName("FrameScore 는 스트라이크인 경우 점수는 반드시 10점이어야 한다.")
    void validate_strike(int score) {
        assertThatThrownBy(() -> FrameScore.strike(Score.of(score)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("스트라이크의 점수는 반드시 " + FrameScore.MAX_SCORE_PER_FRAME + " 점 입니다.");
    }
}