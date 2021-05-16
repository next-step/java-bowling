package bowling.domain.pin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class NormalPinsTest {

    private NormalPins normalPins;

    @BeforeEach
    void setup() {
        normalPins = new NormalPins();
    }

    @ParameterizedTest
    @CsvSource(value = {"10:true", "9:false", "8:false", "7:false", "6:false",
            "5:false", "4:false", "1:false", "3:false", "0:false"}, delimiter = ':')
    @DisplayName("스트라이크를 판단할 수 있다")
    void canDetermineStrike(int rawFirstPin, boolean expected) {
        normalPins.bowl(rawFirstPin);
        assertThat(normalPins.isStrike()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:0:false", "9:1:true", "8:2:true", "7:3:true", "6:4:true",
            "5:5:true", "4:3:false", "8:1:false", "5:3:false", "0:2:false"}, delimiter = ':')
    @DisplayName("스페어를 판단할 수 있다")
    void canDetermineSpare(int rawFirstPin, int rawSecondPin, boolean expected) {
        normalPins.bowl(rawFirstPin);
        normalPins.bowl(rawSecondPin);
        assertThat(normalPins.isSpare()).isEqualTo(expected);
    }

}
