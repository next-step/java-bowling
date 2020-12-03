package bowling.domain.pin;

import bowling.domain.pin.exception.InvalidMaximumPinException;
import bowling.domain.pin.exception.InvalidMinimumPinException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("쓰러진 핀 테스트")
public class PinTest {
    @DisplayName("쓰러트린 핀 개수가 0개 미만일때")
    @ParameterizedTest
    @ValueSource(ints = {-2, -1})
    public void invalidMinPin(int pins) {
        assertThatThrownBy(() -> {
            Pin.of(pins);
        }).isInstanceOf(InvalidMinimumPinException.class);
    }

    @DisplayName("쓰러틀니 핀 개수가 10개 초과일때")
    @ParameterizedTest
    @ValueSource(ints = {11, 12, 13})
    public void invalidMaxPin(int pins) {
        assertThatThrownBy(() -> {
            Pin.of(pins);
        }).isInstanceOf(InvalidMaximumPinException.class);
    }

    @DisplayName("핀을 전부 쓰려트렸을 때")
    @ParameterizedTest
    @CsvSource(value = {"0:false", "1:false", "9:false", "10:true"}, delimiter = ':')
    public void isStrike(int pins, boolean expectedResult) {
        assertThat(Pin.of(pins).isAllFell()).isEqualTo(expectedResult);
    }

    @DisplayName("핀을 하나도 못 쓰러트렸을 때")
    @ParameterizedTest
    @CsvSource(value = {"0:true", "1:false", "9:false", "10:false"}, delimiter = ':')
    public void isGutter(int pins, boolean expectedResult) {
        assertThat(Pin.of(pins).isNoneFell()).isEqualTo(expectedResult);
    }

    @DisplayName("나머지 핀을 쓰러트렸을 때")
    @ParameterizedTest
    @CsvSource(value = {"0:false", "1:false", "9:true", "10:false"}, delimiter = ':')
    public void isSpare(int pins, boolean expectedResult) {
        assertThat(Pin.of(1).isRestFell(pins)).isEqualTo(expectedResult);
    }

}