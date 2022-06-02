package bowling.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class BonusTest {

    @Test
    @DisplayName("보너스 투구 시, 최종 점수 확인")
    void bonusBowling() {
        State bonus = Ready.of(10).bonusBowling(10);
        assertThat(bonus.bonusBowling(10).totalScore()).isEqualTo(30);
    }

    @ParameterizedTest(name = "Bonus 프레임의 보너스 투구 시 최종 점수 계산 - {0}")
    @MethodSource("calculateScoreCondition")
    void calculateScore(State bonus, int expectedScore) {
        assertThat(bonus.totalScore()).isEqualTo(expectedScore);
    }

    private static Stream<Arguments> calculateScoreCondition() {
        State strike = Ready.of(10);
        State spare = Ready.of(5).bowling(5);

        State spareBonus = spare.bonusBowling(10);
        State doubleBonus = strike.bonusBowling(10);
        State tripleBonus = doubleBonus.bonusBowling(10);

        return Stream.of(
                Arguments.of(spareBonus, 20),
                Arguments.of(doubleBonus, 20),
                Arguments.of(tripleBonus, 30)
        );
    }
}
