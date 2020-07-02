package bowling.domain.game;

import bowling.exception.game.CanNotAccessMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("첫 번째 프레임에서 STRIKE 인 경우 현재 프레임을 반환한다.")
    void strike(int countOfPins) {
        FinalFrame finalFrame = FinalFrame.init();
        assertThat(finalFrame.next(countOfPins)).isEqualTo(finalFrame);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("첫 번째 프레임에서 STRIKE 가 아닌 경우 현재 프레임을 반환한다.")
    void not_strike(int countOfPins) {
        FinalFrame finalFrame = FinalFrame.init();
        assertThat(finalFrame.next(countOfPins)).isEqualTo(finalFrame);
    }

    @ParameterizedTest
    @ValueSource(ints = 10)
    @DisplayName("첫 번째 프레임에서 STRIKE 인 경우 bonus 투구가 가능하다.")
    void strike_next_true(int countOfPins) {
        FinalFrame finalFrame = FinalFrame.init();
        assertThat(finalFrame.next(countOfPins).canBowling()).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({"1,9", "2,8"})
    @DisplayName("두 번째 프레임에서 SPARE 인 경우 bonus 투구가 가능하다.")
    void spare_next_true(int firstPins, int secondPins) {
        FinalFrame finalFrame = FinalFrame.init();
        assertThat(finalFrame.next(firstPins).next(secondPins).canBowling()).isEqualTo(true);
    }

    @ParameterizedTest
    @CsvSource({"1,8", "2,5"})
    @DisplayName("SPARE 나 STRIKE 가 아닌 경우 bonus 투구가 불가능하다.")
    void miss_next_false(int firstPins, int secondPins) {
        FinalFrame finalFrame = FinalFrame.init();
        assertThat(finalFrame.next(firstPins).next(secondPins).canBowling()).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({"10, 1, 10"})
    @DisplayName("STRIKE 인 경우 다음 투구 시 두 번의 보너스 공을 투구할 수 있다.")
    void strike_two_bonus(int firstPins, int bonusPins, int secondBonusPins) {
        FinalFrame finalFrame = FinalFrame.init();
        assertThat(finalFrame.next(firstPins).next(bonusPins).next(secondBonusPins).canBowling()).isEqualTo(false);
    }

    @ParameterizedTest
    @CsvSource({"10, 1, 4"})
    @DisplayName("투구를 모두 완료한 후 next 메소드 호출 시 예외를 반환한다. (스트라이크 + 보너스 프레임)")
    void strike_and_bonus_exception(int firstPins, int bonusPins, int errorPins) {
        FinalFrame finalFrame = FinalFrame.init();
        assertThatThrownBy(() -> finalFrame.next(firstPins).next(bonusPins).next(bonusPins).next(errorPins))
                .isInstanceOf(CanNotAccessMethod.class);
    }

    @ParameterizedTest
    @CsvSource({"1, 2, 4"})
    @DisplayName("투구를 모두 완료한 후 next 메소드 호출 시 예외를 반환한다. (Miss)")
    void miss_and_bonus_exception(int firstPins, int bonusPins, int errorPins) {
        FinalFrame finalFrame = FinalFrame.init();
        assertThatThrownBy(() -> finalFrame.next(firstPins).next(bonusPins).next(errorPins))
                .isInstanceOf(CanNotAccessMethod.class);
    }

    @ParameterizedTest
    @CsvSource({"1, 9, 5", "2, 8, 1"})
    @DisplayName("SPARE 후 투구 시 bonus 볼에 저장된 후 더이상의 투구는 불가능하다.")
    void spare_one_bonus(int firstPins, int secondPins, int bonusPins) {
        FinalFrame finalFrame = FinalFrame.init();
        assertThat(finalFrame.next(firstPins).next(secondPins).next(bonusPins).canBowling()).isEqualTo(false);
    }
}