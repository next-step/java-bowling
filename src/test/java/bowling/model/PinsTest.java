package bowling.model;

import bowling.ExceptionMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PinsTest {

    @ParameterizedTest
    @ValueSource(ints = {-2, -1, -10})
    @DisplayName("Pins 생성 실패 : 0이하인 경우 ")
    void create_fail_min(int countOfPins) {
        assertThatThrownBy(() -> new Pins(countOfPins))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.PINS_MIN_EXCEPTION);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:9", "3:7", "8:2"}, delimiter = ':')
    @DisplayName("남은 핀의 갯수로 Pins 생성하기")
    void remainPins(int fallenPins, int remainPins) {
        // given
        Pins expected = new Pins(remainPins);

        // when
        Pins result = Pins.remainPins(fallenPins);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"8:1:7", "3:1:2", "10:10:0"}, delimiter = ':')
    @DisplayName("핀 넘어뜨리기")
    void fallingPins(int initPins, int fallenPins, int expectedPins) {
        // given
        Pins pins = new Pins(initPins);
        Pins expected = new Pins(expectedPins);

        // when
        Pins result = pins.fallingPins(fallenPins);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"8:9", "3:7", "9:10"}, delimiter = ':')
    @DisplayName("핀 넘어뜨리기 : 넘어뜨릴 핀의 갯수가 남은 핀의 갯수보다 큰 경우")
    void fallingPins_fail(int initPins, int fallenPins) {
        // given
        Pins pins = new Pins(initPins);

        // when
        assertThatThrownBy(() -> pins.fallingPins(fallenPins))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.PINS_LAST_PINS_EXCEPTION);
    }

    @Test
    @DisplayName("모든 핀이 넘어졌는지 확인하기")
    void areAllPinsFallen() {
        // given
        Pins pins = new Pins();

        // when
        Pins result = pins.fallingPins(10);

        // then
        assertThat(result.areAllPinsFallen()).isEqualTo(true);
    }

}
