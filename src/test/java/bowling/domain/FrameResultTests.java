package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class FrameResultTests {
    @DisplayName("값을 하나만 입력받아서 알맞는 상태를 찾을 수 있다.")
    @ParameterizedTest
    @MethodSource("findByOneResource")
    void findByOneTest(NumberOfHitPin inputValue, FrameResult expectedStatus) {
        assertThat(FrameResult.find(inputValue)).isEqualTo(expectedStatus);
    }
    public static Stream<Arguments> findByOneResource() {
        return Stream.of(
                Arguments.of(new NumberOfHitPin(0), FrameResult.GUTTER),
                Arguments.of(new NumberOfHitPin(3), FrameResult.THREE),
                Arguments.of(new NumberOfHitPin(10), FrameResult.STRIKE)
        );
    }

    @DisplayName("값을 두개 입력받아서 알맞는 상태를 찾을 수 있다.")
    @ParameterizedTest
    @MethodSource("findByTwoResource")
    void findByTwoTest(NumberOfHitPin firstValue, NumberOfHitPin secondValue, FrameResult expectedStatus) {
        assertThat(FrameResult.find(firstValue, secondValue)).isEqualTo(expectedStatus);
    }
    public static Stream<Arguments> findByTwoResource() {
        return Stream.of(
                Arguments.of(new NumberOfHitPin(5), new NumberOfHitPin(5), FrameResult.SPARE),
                Arguments.of(new NumberOfHitPin(5), new NumberOfHitPin(4), FrameResult.FOUR),
                Arguments.of(new NumberOfHitPin(5), new NumberOfHitPin(0), FrameResult.GUTTER)
        );
    }
}
