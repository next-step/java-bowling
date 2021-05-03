package bowling.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class StringUtilsTest {

    static Stream<Arguments> alignCenterSource() {
        return Stream.of(
                arguments("ksd", 5, " ksd "),
                arguments("ksd", 3, "ksd"),
                arguments("ksd", 2, "ksd"),
                arguments("ksd", 4, "ksd "),
                arguments(null, 4, "    ")
        );
    }

    @Test
    @DisplayName("size가 padString보다 크면, padding이 적용된 문자열이 반환된다.")
    void padLeft() {
        // given
        final String padString = "ksd";
        final int size = 5;

        // when
        final String paddedString = StringUtils.padLeft(padString, size);

        // then
        assertThat(paddedString).isEqualTo("  ksd");
    }

    @Test
    @DisplayName("size가 padString보다 작으면, padString이 그대로 반환된다.")
    void padLeftStringBiggerThanSize() {
        // given
        final String padString = "ksd";
        final int size = 2;

        // when
        final String paddedString = StringUtils.padLeft(padString, size);

        // then
        assertThat(paddedString).isEqualTo("ksd");
    }

    @Test
    @DisplayName("size가 숫자 길이보다 크면, padding이 적용된 문자열이 반환된다.")
    void padZero() {
        // given
        final int number = 50;
        final int size = 3;

        // when
        final String paddedNumber = StringUtils.padZero(number, size);

        // then
        assertThat(paddedNumber).isEqualTo("050");
    }

    @Test
    @DisplayName("size가 숫자 길이보다 작으면, 숫자는 문자열형태로 반환된다.")
    void padZeroNumberLengthBiggerThanSize() {
        // given
        final int number = 5000;
        final int size = 3;

        // when
        final String paddedNumber = StringUtils.padZero(number, size);

        // then
        assertThat(paddedNumber).isEqualTo("5000");
    }

    @ParameterizedTest
    @MethodSource("alignCenterSource")
    @DisplayName("주어진 경우에 해당하는 문자열이 반환된다.")
    void alignCenter(String padString, int size, String expected) {
        // given
        // when
        final String paddedString = StringUtils.alignCenter(padString, size);

        // then
        assertThat(paddedString).isEqualTo(expected);
    }
}
