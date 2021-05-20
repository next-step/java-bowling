package bowling.domain.pin;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NormalPinsTest {

    private NormalPins normalPins;

    @BeforeEach
    void setup() {
        normalPins = new NormalPins();
    }

    @ParameterizedTest
    @CsvSource(value = {"10:true", "1:false", "0:false"}, delimiter = ':')
    @DisplayName("스트라이크를 판단할 수 있다")
    void canDetermineStrike(int rawFirstPin, boolean expected) {
        normalPins.bowl(rawFirstPin);
        assertThat(normalPins.isStrike()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"8:2:true", "0:10:true", "4:3:false"}, delimiter = ':')
    @DisplayName("스페어를 판단할 수 있다")
    void canDetermineSpare(int rawFirstPin, int rawSecondPin, boolean expected) {
        normalPins.bowl(rawFirstPin);
        normalPins.bowl(rawSecondPin);
        assertThat(normalPins.isSpare()).isEqualTo(expected);
    }

    @Test
    @DisplayName("한번 세워진 핀보다 더 많은 핀을 쓰러뜨리려고 하면 INVALID_SECOND_PIN을 던진다")
    void bowlOverflowThrowsException() {
        normalPins.bowl(7);
        CustomException customException = assertThrows(CustomException.class, () -> normalPins.bowl(5));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_SECOND_PIN);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0:0", "8:2:3"}, delimiter = ':')
    @DisplayName("정해진 횟수보다 더 많이 던지면 INVALID_BOWL을 던진다")
    void tooMuchBowlThrowsException(int rawFirstPin, int rawSecondPin, int rawThirdPin) {
        normalPins.bowl(rawFirstPin);
        normalPins.bowl(rawSecondPin);
        CustomException customException = assertThrows(CustomException.class, () -> normalPins.bowl(rawThirdPin));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_BOWL);
    }

}
