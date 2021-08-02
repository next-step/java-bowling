package bowling.view.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("숫자를 볼링 게임에 맞는 기호로 바꾸는 enum 테스트")
class NumberSymbolTest {

    @DisplayName("각 숫자에 맞는 기호를 반환한다")
    @MethodSource
    @ParameterizedTest
    void convert(int number, String expectedSymbol) {
        assertThat(NumberSymbol.convert(number)).isEqualTo(expectedSymbol);
    }

    private static Stream<Arguments> convert() {
        return Stream.of(
                Arguments.of(0, "-"),
                Arguments.of(1, "1"),
                Arguments.of(2, "2"),
                Arguments.of(3, "3"),
                Arguments.of(4, "4"),
                Arguments.of(5, "5"),
                Arguments.of(6, "6"),
                Arguments.of(7, "7"),
                Arguments.of(8, "8"),
                Arguments.of(9, "9"),
                Arguments.of(10, "X")
        );
    }
}
