package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class PinExpressionTest {

    @DisplayName("볼링 핀에 대한 표현식을 반환")
    @ParameterizedTest
    @MethodSource
    public void convert(final int count, final String expected) {
        assertThat(PinExpression.convert(count))
                .isEqualTo(expected);
    }

    private static Stream<Arguments> convert() {
        return Stream.of(
                Arguments.of(0, "-"),
                Arguments.of(10, "X"),
                Arguments.of(1, "1"),
                Arguments.of(9, "9")
        );
    }
}
