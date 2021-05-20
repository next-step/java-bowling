package bowling.domain.pin;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PinTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 6, 10})
    @DisplayName("핀을 생성할 수 있다")
    void canGenerateValidPin(int rawPin) {
        Pin pin = new Pin(rawPin);
        assertThat(pin.getClass()).isEqualTo(Pin.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11, 0x7fffffff})
    @DisplayName("올바르지 않은 핀을 생성하려 하면 INVALID_PIN을 던진다")
    void invalidPinThrowsException(int rawPin) {
        CustomException customException = assertThrows(CustomException.class, () -> new Pin(rawPin));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_PIN);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0, 10", "1 ,9", "3,5"})
    @DisplayName("핀을 2개 생성할 수 있다")
    void canGenerateTwoPins(String rawPins) {
        List<Integer> rawPinValues = Arrays.stream(rawPins.split(","))
                .map(rawPin -> Integer.parseInt(rawPin.trim()))
                .collect(Collectors.toList());
        Pin firstPin = new Pin(rawPinValues.get(0));
        Pin secondPin = new Pin(firstPin, rawPinValues.get(1));
        assertThat(secondPin.getClass()).isEqualTo(Pin.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"2, 9", "1 ,10", "6,8"})
    @DisplayName("두 번째 핀이 잘못됐을 경우 INVALID_SECOND_PIN을 던진다")
    void invalidSecondPinThrowsException(String rawPins) {
        List<Integer> rawPinValues = Arrays.stream(rawPins.split(","))
                .map(rawPin -> Integer.parseInt(rawPin.trim()))
                .collect(Collectors.toList());
        Pin firstPin = new Pin(rawPinValues.get(0));

        int secondPinValue = rawPinValues.get(1);
        CustomException customException = assertThrows(CustomException.class, () -> new Pin(firstPin, secondPinValue));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_SECOND_PIN);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:true", "6:false", "0:false"}, delimiter = ':')
    @DisplayName("스트라이크 여부를 판단할 수 있다")
    void canDetermineStrike(int rawPin, boolean expected) {
        Pin pin = new Pin(rawPin);
        assertThat(pin.didClear()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"9:1:true", "0:10:true", "0:9:false", "3:2:false"}, delimiter = ':')
    @DisplayName("스페어 여부를 판단할 수 있다")
    void canDetermineSpare(int rawFirstPin, int rawSecondPin, boolean expected) {
        Pin firstPin = new Pin(rawFirstPin);
        Pin secondPin = new Pin(firstPin, rawSecondPin);
        assertThat(secondPin.didClear(firstPin)).isEqualTo(expected);
    }

}
