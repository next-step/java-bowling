package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class PinsTest {
    @DisplayName("쓰러트린 핀의 갯수를 넣어 객체 생성")
    @ParameterizedTest(name = "{displayName}; 핀 갯수: {0}")
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void create(int falledPins) {
        new Pins(falledPins);
    }

    @DisplayName("범위 밖의 핀 갯수가 들어오면 예외를 던진다.")
    @ParameterizedTest(name = "{displayName}; 핀 갯수: {0}")
    @ValueSource(ints = {-1, 11})
    void createWithOutOfRangePins(int falledPins) {
        assertThatThrownBy(() -> new Pins(falledPins))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스트라이크 여부를 확인한다.")
    @ParameterizedTest(name = "{displayName}; 핀 갯수: {0}")
    @CsvSource({
        "10,true",
        "9,false",
        "1,false",
        "0,false"})
    void checkStrike(int pins, boolean expected) {
        assertThat(new Pins(pins).isStrike()).isEqualTo(expected);
    }

    @DisplayName("스페어 여부를 확인한다.")
    @ParameterizedTest(name = "{displayName}; 핀 갯수: {0}, {1}")
    @CsvSource({
        "10,0, false",
        "4,6,true",
        "5,4,false",
        "0,10,true"})
    void checkStrike(int firstPins, int secondPins, boolean expected) {
        assertThat(new Pins(firstPins).isSpare(new Pins(secondPins))).isEqualTo(expected);
    }
}
