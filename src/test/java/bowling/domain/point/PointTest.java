package bowling.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PointTest {

    private Point createPoint(int point) {
        return new Point(point);
    }

    @Test
    @DisplayName("0보다 작은 경우 Exception")
    void negativeNumberTest() {
        assertThatThrownBy(() -> this.createPoint(-1))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("10보다 큰 경우 Exception")
    void biggerThan10Test() {
        assertThatThrownBy(() -> this.createPoint(11))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @MethodSource("provideNumbers")
    @DisplayName("point 정상 생성")
    void createPointTest(int value) {
        Point point = createPoint(value);
        assertThat(point.getPoint()).isEqualTo(value);
    }

    private static Stream<Arguments> provideNumbers() {
        return Stream.of(
                Arguments.of(1),
                Arguments.of(2),
                Arguments.of(3),
                Arguments.of(4),
                Arguments.of(5),
                Arguments.of(6),
                Arguments.of(7),
                Arguments.of(8),
                Arguments.of(9),
                Arguments.of(10)
        );
    }
}