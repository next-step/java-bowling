package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NormalFrameStatusTests {
    @DisplayName("값을 하나만 입력받아서 알맞는 상태를 찾을 수 있다.")
    @ParameterizedTest
    @MethodSource("findByOneResource")
    void findByOneTest(NumberOfHitPin inputValue, FrameStatus expectedStatus) {
        assertThat(FrameStatus.find(inputValue)).isEqualTo(expectedStatus);
    }
    public static Stream<Arguments> findByOneResource() {
        return Stream.of(
                Arguments.of(new NumberOfHitPin(0), FrameStatus.GUTTER),
                Arguments.of(new NumberOfHitPin(3), FrameStatus.THREE),
                Arguments.of(new NumberOfHitPin(10), FrameStatus.STRIKE)
        );
    }

    @DisplayName("값을 두개 입력받아서 알맞는 상태를 찾을 수 있다.")
    @ParameterizedTest
    @MethodSource("findByTwoResource")
    void findByTwoTest(NumberOfHitPin firstValue, NumberOfHitPin secondValue, FrameStatus expectedStatus) {
        assertThat(FrameStatus.find(firstValue, secondValue)).isEqualTo(expectedStatus);
    }
    public static Stream<Arguments> findByTwoResource() {
        return Stream.of(
                Arguments.of(new NumberOfHitPin(5), new NumberOfHitPin(5), FrameStatus.SPARE),
                Arguments.of(new NumberOfHitPin(5), new NumberOfHitPin(4), FrameStatus.FOUR),
                Arguments.of(new NumberOfHitPin(5), new NumberOfHitPin(0), FrameStatus.GUTTER)
        );
    }
}
