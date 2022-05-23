package bowling.domain.score;

import bowling.exception.InvalidBonusCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BonusCountTest {

    @ParameterizedTest
    @MethodSource("bonusCountProvider")
    @DisplayName("보너스 횟수를 확인한다")
    void strike(BonusCount bonusCount, int decreaseCount) {
        //when
        for (int count = 0; count < decreaseCount; count++) {
            bonusCount = bonusCount.decrease();
        }

        //then
        assertThat(bonusCount.isEmpty()).isTrue();
    }

    public static Stream<Arguments> bonusCountProvider() {
        return Stream.of(
                arguments(BonusCount.strike(), 2),
                arguments(BonusCount.spare(), 1),
                arguments(BonusCount.none(), 0)
        );
    }

    @Test
    @DisplayName("추가할 보너스가 없는 것을 확인한다")
    void isEmpty() {
        //given
        BonusCount none = BonusCount.none();

        //when, then
        assertThat(none.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("추가할 보너스를 줄이는 행위를 확인한다")
    void decreaseBonusCount() {
        //given
        BonusCount spareBonusCount = BonusCount.spare();

        //when
        BonusCount decreasedBonusCount = spareBonusCount.decrease();

        //then
        assertThat(decreasedBonusCount.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("추가할 보너스 점수가 없는 상태에서 값을 줄이면 예외를 발생한다")
    void decreaseWhenBonusCountIsEmpty() {
        //given
        BonusCount none = BonusCount.none();

        //when, then
        assertThatThrownBy(none::decrease).isInstanceOf(InvalidBonusCountException.class);
    }

}