package bowling.domain.bonusScore;

import bowling.domain.NumberOfHitPin;
import bowling.domain.exceptions.InvalidBonusScoreArgumentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalBonusScoreTests {
    @DisplayName("NumberOfHitPin 두개를 입력 받아서 객체를 생성할 수 있다.")
    @Test
    void createTest() {
        NormalBonusScore normalBonusScore = NormalBonusScore.of(new NumberOfHitPin(5), new NumberOfHitPin(5));

        assertThat(normalBonusScore).isEqualTo(new NormalBonusScore(5, 5, true));
    }

    @DisplayName("null이 섞여서 들어오는 경우 0으로 처리한다.")
    @ParameterizedTest
    @MethodSource("nullCalculateResource")
    void nullCalculateTest(NumberOfHitPin firstPin, NumberOfHitPin secondPin, NormalBonusScore expectedResult) {
        assertThat(NormalBonusScore.of(firstPin, secondPin)).isEqualTo(expectedResult);
    }
    public static Stream<Arguments> nullCalculateResource() {
        return Stream.of(
                Arguments.of(
                        new NumberOfHitPin(10),
                        null,
                        new NormalBonusScore(10, 0, true)
                ),
                Arguments.of(
                        new NumberOfHitPin(5),
                        new NumberOfHitPin(4),
                        new NormalBonusScore(5, 4, true)
                ),
                Arguments.of(
                        null,
                        null,
                        new NormalBonusScore(0, 0, false)
                )
        );
    }

    @DisplayName("첫번째 핀만 null인 경우는 존재할 수 없다.")
    @Test
    void nullValidationTest() {
        assertThatThrownBy(() -> NormalBonusScore.of(null, new NumberOfHitPin(5)))
                .isInstanceOf(InvalidBonusScoreArgumentException.class);
    }

    @DisplayName("현재 보너스 점수가 스트라이크용 보너스로 사용될 수 있는지 상태를 관리한다.")
    @Test
    void isStrikeBonusTest() {
        assertThat(NormalBonusScore.of(new NumberOfHitPin(5), null))
                .isEqualTo(new NormalBonusScore(5, 0, false));
    }

    @DisplayName("스트라이크인 경우에도 스트라이크 보너스로 사용 가능하다.")
    @Test
    void isStrikeBonusWhenStrikeTest() {
        assertThat(NormalBonusScore.of(new NumberOfHitPin(10), null))
                .isEqualTo(new NormalBonusScore(10, 0, true));
    }
}
