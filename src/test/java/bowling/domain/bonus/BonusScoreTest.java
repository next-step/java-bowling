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

class BonusScoreTest {

    @Test
    @DisplayName("Strike일 경우 보너스 점수를 추가 할 수 있는지 테스트")
    void strikeAvailableAddBonusScoreTest() {
        BonusScore bonusScore = BonusScore.strikeBonusScore(0);
        bonusScore.addPoint(10);
        assertThat(bonusScore.isAvailableAddBonusPoint()).isTrue();
        bonusScore.addPoint(1);
        assertThat(bonusScore.isAvailableAddBonusPoint()).isFalse();
    }

    @Test
    @DisplayName("Spare일 경우 보너스 점수를 추가 할 수 있는지 테스트")
    void spareAvailableAddBonusScoreTest() {
        BonusScore bonusScore = BonusScore.spareBonusScore(0);
        assertThat(bonusScore.isAvailableAddBonusPoint()).isTrue();
        bonusScore.addPoint(10);
        assertThat(bonusScore.isAvailableAddBonusPoint()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("provideStrikeBonusScores")
    @DisplayName("strike 보너스 점수 계산 테스트")
    void strikeCalculateBonusScoreTest(List<Integer> points, int totalScore) {
        BonusScore bonusScore = BonusScore.strikeBonusScore(0);
        for (Integer point : points) {
            bonusScore.addPoint(point);
        }

        assertThat(bonusScore.calculateBonusPoints()).isEqualTo(totalScore);
    }

    private static Stream<Arguments> provideStrikeBonusScores() {
        return Stream.of(
                Arguments.of(Arrays.asList(10, 5), 15),
                Arguments.of(Arrays.asList(1, 2), 3),
                Arguments.of(Arrays.asList(5, 4), 9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideSpareBonusScores")
    @DisplayName("spare 보너스 점수 계산 테스트")
    void spareCalculateBonusScoreTest(Integer point, int totalScore) {
        BonusScore bonusScore = BonusScore.strikeBonusScore(0);
        bonusScore.addPoint(point);

        assertThat(bonusScore.calculateBonusPoints()).isEqualTo(totalScore);
    }

    private static Stream<Arguments> provideSpareBonusScores() {
        return Stream.of(
                Arguments.of(10, 10),
                Arguments.of(1, 1),
                Arguments.of(5, 5)
        );
    }
}