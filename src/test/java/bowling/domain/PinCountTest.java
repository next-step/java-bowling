package bowling.domain;

import bowling.domain.frame.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PinCountTest {

    @Test
    void create() {
        int pintCountInInt = 1;
        PinCount actual = new PinCount(pintCountInInt);

        assertThat(actual).isEqualTo(new PinCount(pintCountInInt));
    }

    @Test
    @DisplayName("pinCount가 0 보다 작을때 예외 발생 테스트")
    void when_pin_count_is_smaller_than_0_throws_exception() {
        int pintCountInInt = -1;

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PinCount(pintCountInInt));
    }

    @Test
    @DisplayName("pinCount가 10 보다 클때 예외 발생 테스트")
    void when_frame_number_is_bigger_than_10_throws_exception() {
        int pintCountInInt = 11;

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PinCount(pintCountInInt));
    }

    @Test
    @DisplayName("pinCount가 인자가 숫자가 아닐떄 예외 발생 테스트")
    void when_frame_number_is_not_number_throws_exception() {
        String pintCountInInt = "A";

        assertThatIllegalArgumentException().isThrownBy(() ->
                new PinCount(pintCountInInt));
    }

    @ParameterizedTest
    @CsvSource(value = {"0:false", "1:false", "5:false", "7:false", "10:true"}, delimiter = ':')
    void is_strike(String pinCount, String expectedResult) {
        PinCount actualPinCount = new PinCount(pinCount);

        assertThat(actualPinCount.isStrike()).isEqualTo(Boolean.valueOf(expectedResult));
    }


    @ParameterizedTest
    @CsvSource(value = {"0:true", "1:false", "5:false", "7:false", "10:false"}, delimiter = ':')
    void is_gutter(String pinCount, String expectedResult) {
        PinCount actualPinCount = new PinCount(pinCount);

        assertThat(actualPinCount.isGutter()).isEqualTo(Boolean.valueOf(expectedResult));
    }


    @ParameterizedTest
    @CsvSource(value = {"3:4:true", "10:8:false", "8:8:false", "5:6:false", "7:10:false", "1:9:true"}, delimiter = ':')
    void is_valid(String first, String second, String expectedResult) {
        PinCount firstPinCount = new PinCount(first);
        PinCount secondPinCount = new PinCount(second);

        assertThat(firstPinCount.isValid(secondPinCount)).isEqualTo(Boolean.valueOf(expectedResult));
        assertThat(secondPinCount.isValid(firstPinCount)).isEqualTo(Boolean.valueOf(expectedResult));
    }

}
