package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ScoresTest {
    @DisplayName("볼링 점수 생성")
    @Test
    void create() {
        assertThatCode(() -> new Scores());
    }

    @DisplayName("점수 저장")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void addScore(int expected) {
        Scores scores = new Scores();
        scores.add(expected);
        assertThat(scores.getTotalScore()).isEqualTo(expected);
    }

    @DisplayName("점수의 합이 10이 넘을 경우 throws Exception")
    @ParameterizedTest
    @MethodSource("scoreValues")
    void addScoreFailByInvalidTotal(List<Integer> values) {
        Scores scores = new Scores();
        scores.add(values.get(0));
        assertThatIllegalArgumentException().isThrownBy(() -> scores.add(values.get(1)));
    }

    static Stream<Arguments> scoreValues() {
        return Stream.of(
                arguments(Arrays.asList(5, 6)),
                arguments(Arrays.asList(10, 1))
        );
    }
}