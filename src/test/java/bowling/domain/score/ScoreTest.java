package bowling.domain.score;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ScoreTest {
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    @DisplayName("점수는 0 ~ 10 의 숫자만 가능하다.")
    void validate_score(int score) {
        assertThatThrownBy(() -> Score.of(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("점수는 0 에서 10 까지의 숫자만 가능합니다.");
    }

    @ParameterizedTest
    @MethodSource("printScore")
    @DisplayName("점수 출력 테스트")
    void print_score(int score, String expected) {
        assertThat(Score.of(score).toString()).isEqualTo(expected);
    }

    static Stream<Arguments> printScore() {
        return Stream.of(
                arguments(0, "-"),
                arguments(1, "1"),
                arguments(10, "X"));
    }
}