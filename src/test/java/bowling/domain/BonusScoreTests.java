package bowling.domain;

import bowling.domain.exceptions.ExceedBonusLimitException;
import bowling.domain.exceptions.InvalidBonusScoreArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusScoreTests {
    @DisplayName("NumberOfHitPin 두개를 입력 받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        BonusScore bonusScore = BonusScore.of(new NumberOfHitPin(5), new NumberOfHitPin(5));

        assertThat(bonusScore).isEqualTo(new BonusScore(5, 5, true));
    }

    @DisplayName("null이 섞여서 들어오는 경우 0으로 처리한다.")
    @ParameterizedTest
    @MethodSource("nullCalculateResource")
    void nullCalculateTest(NumberOfHitPin firstPin, NumberOfHitPin secondPin, BonusScore expectedResult) {
        assertThat(BonusScore.of(firstPin, secondPin)).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> nullCalculateResource() {
        return Stream.of(
                Arguments.of(
                        new NumberOfHitPin(10),
                        null,
                        new BonusScore(10, 0, true)
                ),
                Arguments.of(
                        new NumberOfHitPin(5),
                        new NumberOfHitPin(4),
                        new BonusScore(5, 4, true)
                ),
                Arguments.of(
                        null,
                        null,
                        new BonusScore(0, 0, false)
                )
        );
    }

    @DisplayName("첫번째 핀만 null인 경우는 존재할 수 없다.")
    @Test
    void nullValidationTest() {
        assertThatThrownBy(() -> BonusScore.of(null, new NumberOfHitPin(5)))
                .isInstanceOf(InvalidBonusScoreArgumentException.class);
    }

    @DisplayName("보너스 점수의 합산은 10을 넘을 수 없다.")
    @Test
    void maxValidationTest() {
        assertThatThrownBy(() -> BonusScore.of(new NumberOfHitPin(5), new NumberOfHitPin(6)))
                .isInstanceOf(ExceedBonusLimitException.class);
    }

    @DisplayName("현재 보너스 점수가 스트라이크용 보너스로 사용될 수 있는지 상태를 관리한다.")
    @Test
    void isStrikeBonusTest() {
        assertThat(BonusScore.of(new NumberOfHitPin(5), null))
                .isEqualTo(new BonusScore(5, 0, false));
    }

    @DisplayName("스트라이크인 경우에도 스트라이크 보너스로 사용 가능하다.")
    @Test
    void isStrikeBonusWhenStrikeTest() {
        assertThat(BonusScore.of(new NumberOfHitPin(10), null))
                .isEqualTo(new BonusScore(10, 0, true));
    }
}
