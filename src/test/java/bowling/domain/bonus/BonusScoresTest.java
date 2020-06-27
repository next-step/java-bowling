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
import static org.assertj.core.api.Assertions.assertThatCode;

class BonusScoresTest {

    private BonusScores createBonusScores() {
        return new BonusScores();
    }

    @Test
    @DisplayName("생성한 BonusScore에 각 케이스 생성 테스트")
    void createAddableBonusScores() {
        BonusScores bonusScores = createBonusScores();

        assertThatCode(() -> bonusScores.addBonusScore(BonusScore.strikeBonusScore(0)));
        assertThatCode(() -> bonusScores.addBonusScore(BonusScore.spareBonusScore(1)));
        assertThatCode(() -> bonusScores.addBonusScore(BonusScore.noneBonusScores(2)));

    }

    @ParameterizedTest
    @MethodSource("provideStrikeBonusScores")
    @DisplayName("strike bonusScore test")
    void addStrikeBonusPoint(List<Integer> points, int totalPoint) {
        BonusScores bonusScores = createBonusScores();
        BonusScore strikeBonusScore = BonusScore.strikeBonusScore(0);
        bonusScores.addBonusScore(strikeBonusScore);
        for (Integer point : points) {
            bonusScores.addBonusPoint(point);
        }
        assertThat(strikeBonusScore.calculateBonusPoints()).isEqualTo(totalPoint);
    }

    private static Stream<Arguments> provideStrikeBonusScores() {
        return Stream.of(
                Arguments.of(Arrays.asList(10, 1), 11),
                Arguments.of(Arrays.asList(5, 4), 9)
        );
    }
}