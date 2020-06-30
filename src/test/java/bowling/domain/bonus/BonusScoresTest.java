package bowling.domain.bonus;

import bowling.domain.score.Scores;
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

    private Scores createStrikeScores() {
        Scores scores = new Scores();
        scores.addScore(10);
        return scores;
    }

    private Scores createSpareScores() {
        Scores scores = new Scores();
        scores.addScore(5);
        scores.addScore(5);
        return scores;
    }

    private Scores createNoneScores() {
        Scores scores = new Scores();
        scores.addScore(5);
        return scores;
    }

    @Test
    @DisplayName("생성한 BonusScore에 각 케이스 생성 테스트")
    void createAddableBonusScores() {
        BonusScores bonusScores = createBonusScores();

        assertThatCode(() -> bonusScores.addBonusScore(createStrikeScores(), 0));
        assertThatCode(() -> bonusScores.addBonusScore(createSpareScores(), 1));
        assertThatCode(() -> bonusScores.addBonusScore(createNoneScores(), 2));

    }

    @ParameterizedTest
    @MethodSource("provideStrikeBonusScores")
    @DisplayName("strike bonusScore test")
    void addStrikeBonusPoint(List<Integer> points, int totalPoint) {
        BonusScores bonusScores = createBonusScores();
        bonusScores.addBonusScore(createStrikeScores(), 0);
        for (Integer point : points) {
            bonusScores.addBonusPoint(point);
        }

        BonusScore strikeBonusScore = bonusScores.findBonusScores(0);
        assertThat(strikeBonusScore.calculateBonusPoints()).isEqualTo(totalPoint);
    }

    private static Stream<Arguments> provideStrikeBonusScores() {
        return Stream.of(
                Arguments.of(Arrays.asList(10, 1), 11),
                Arguments.of(Arrays.asList(5, 4), 9)
        );
    }
}