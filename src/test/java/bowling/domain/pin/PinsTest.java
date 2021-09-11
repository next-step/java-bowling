package bowling.domain.pin;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Pins Test")
public class PinsTest {

    @Test
    @DisplayName("생성")
    void generatePins() {
        // given
        // when
        Pins pins = Pins.of(9, 1);

        //then
        assertThat(pins).isEqualTo(Pins.of(9, 1));
    }

    static Stream<Arguments> provideSumPins() {
        return Stream.of(
                Arguments.of(new int[]{10, 10, 10}, 30),
                Arguments.of(new int[]{10, 9, 1}, 20),
                Arguments.of(new int[]{9, 1, 0}, 10),
                Arguments.of(new int[]{9, 0}, 9),
                Arguments.of(new int[]{5, 3}, 8),
                Arguments.of(new int[]{0, 0}, 0)
        );
    }

    @ParameterizedTest(name = "{0} -> {1}")
    @MethodSource("provideSumPins")
    @DisplayName("pins 합 확인")
    void sumPins(int[] numbers, int actual) {
        //given
        Pins pins = Pins.of(numbers);

        //when
        int sum = pins.sum();

        //then
        assertThat(sum).isEqualTo(actual);
    }
}
