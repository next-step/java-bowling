package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

class PinsTest {
    @DisplayName("Pins 를 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {0,1,9,10})
    void create_Pins_생성(int hitPins) {
        assertThat(Pins.create(hitPins)).isNotNull().isInstanceOf(Pins.class);
    }

    @DisplayName("Pins 쓰러뜨린 핀이 0 ~ 10개 범위를 벗어난 경우 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1, 11, 12})
    void create_Pins_생성_예외(int hitPins) {
        assertThatThrownBy(() -> Pins.create(hitPins)).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("Pins 현 상황이 스트라이크 인지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "0, false",
            "1, false",
            "9, false",
            "10, true"
    })
    void isStrike_Pins_스트라이크_체크(int hitPins, boolean trueOrFalse) {
        Pins pins = Pins.create(hitPins);
        assertThat(pins.isStrike()).isEqualTo(trueOrFalse);
    }

    @DisplayName("Pins 현 상황이 스페어 인지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "0, 9, false",
            "1, 9, true",
            "2, 7, false",
            "9, 1, true"
    })
    void isSpare_Pins_스페어_체크(int firstHitPins, int secondHitPins, boolean trueOrFalse) {
        Pins firstPins = Pins.create(firstHitPins);
        Pins secondPins = Pins.create(secondHitPins);
        assertThat(firstPins.isSpare(secondPins)).isEqualTo(trueOrFalse);
    }

    @DisplayName("Pins 현 상황이 거터 인지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "0, true",
            "1, false",
            "9, false",
            "10, false"
    })
    void isGutter_Pins_거터_체크(int hitPins, boolean trueOrFalse) {
        Pins pins = Pins.create(hitPins);
        assertThat(pins.isGutter()).isEqualTo(trueOrFalse);
    }

    @DisplayName("Pins 첫번째 투구 갯수와 두번째 투구 갯수의 합이 10 초과일 경우 예외가 발생한다.")
    @ParameterizedTest
    @CsvSource(value = {
            "4, 7, true",
            "5, 6, true",
            "8, 2, false",
            "9, 2, true"
    })
    void isOverMaxHitPins_Pins_투구_갯수_체크(int firstHitPins, int secondHitPins, boolean trueOrFalse) {
        Pins firstPins = Pins.create(firstHitPins);
        Pins secondPins = Pins.create(secondHitPins);
        assertThat(firstPins.isOverMaxHitPins(secondPins)).isEqualTo(trueOrFalse);
    }
}