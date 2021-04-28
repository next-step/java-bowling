package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SymbolTest {

    @ParameterizedTest(name = "{0} : {1}")
    @CsvSource(value = {"10, X", "0, -", "1, 1"})
    @DisplayName("첫번째 쓰러뜨린 핀 카운트에 맞는 표식을 반환한다.")
    public void ofFirst(int pinCount, String expected) throws Exception {
        String result = Symbol.ofFirst(new PinCount(pinCount));
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}, {1} : {2}")
    @CsvSource(value = {"10, 10, X", "0, 10, /", "5, 5, /", "10, 0, -", "1, 1, 1"})
    @DisplayName("첫번째 쓰러뜨린 핀을 인자로 받아 두번째 쓰러뜨린 핀 카운트에 맞는 표식을 반환한다.")
    public void ofSecond(int firstPinCount, int secondPinCount, String expected) throws Exception {
        String result = Symbol.ofSecond(new PinCount(firstPinCount), new PinCount(secondPinCount));
        assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "{0}, {1}, {2} : {3}"  )
    @CsvSource(value = {"10, 10, 10, X", "10, 1, 9, /", "10, 1, 8, 8", "10, 1, 0, -", "1, 9, 10, X", "1, 9, 1, 1", "1, 9, 0, -", "10, 10, 0, -"})
    @DisplayName("첫번째, 두번째 쓰러뜨린 핀을 인자로 받아 세번째 쓰러뜨린 핀 카운트에 맞는 표식을 반환한다.")
    public void ofThird(int firstPinCount, int secondPinCount, int thirdPinCount, String expected) throws Exception {
        String result = Symbol.ofThird(new PinCount(firstPinCount), new PinCount(secondPinCount), new PinCount(thirdPinCount));
        assertThat(result).isEqualTo(expected);
    }
}
