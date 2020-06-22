package bowling.domain.bonus;

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

class BonusScoresTest {

    @Test
    @DisplayName("Strike BonusScore일때 2개 이상의 점수를 넣는경우 Exception")
    void strikeBonusScoreExceptionTest() {
        BonusScores strikeBonusScores = BonusScores.strikeBonusScore();
        strikeBonusScores.addPoint(5);
        strikeBonusScores.addPoint(5);
        assertThatThrownBy(() -> strikeBonusScores.addPoint(5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Spare BonusScores일때 1개 이상의 점수를 넣는경우 Exception")
    void spareBonusScoreExceptionTest() {
        BonusScores spareBonusScores = BonusScores.spareBonusScore();
        spareBonusScores.addPoint(5);
        assertThatThrownBy(() -> spareBonusScores.addPoint(5))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideStrikeBonusScore")
    @DisplayName("Strike BonusScore 점수 계산")
    void calculateStrikeBonusScore(List<Integer> points, int sum) {
        BonusScores strikeBonusScores = BonusScores.strikeBonusScore();
        for (Integer point : points) {
            strikeBonusScores.addPoint(point);
        }
        assertThat(strikeBonusScores.calculateBonusScore()).isEqualTo(sum);
    }

    private static Stream<Arguments> provideStrikeBonusScore() {
        return Stream.of(
                Arguments.of(Arrays.asList(5, 5), 10),
                Arguments.of(Arrays.asList(1, 4), 5),
                Arguments.of(Arrays.asList(9, 0), 9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSpareBonusScore")
    @DisplayName("Spare BonusScore 점수 계산")
    void calculateSpareBonusScore(int point) {
        BonusScores spareBonusScores = BonusScores.spareBonusScore();
        spareBonusScores.addPoint(point);

        assertThat(spareBonusScores.calculateBonusScore()).isEqualTo(point);
    }

    private static Stream<Arguments> provideSpareBonusScore() {
        return Stream.of(
                Arguments.of(5),
                Arguments.of(2),
                Arguments.of(10),
                Arguments.of(6),
                Arguments.of(0),
                Arguments.of(1)
        );
    }
}