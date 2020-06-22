package bowling.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class PointsTest {

    @ParameterizedTest
    @MethodSource("providePoints")
    @DisplayName("점수 계산 테스트")
    void calculateTotalPoints(List<Integer> numbers, int sum) {
        Points points = new Points();
        for (Integer number : numbers) {
            points.addPoint(number);
        }
        assertThat(points.sumPoints()).isEqualTo(sum);
    }

    private static Stream<Arguments> providePoints() {
        return Stream.of(
                Arguments.of(Arrays.asList(1,2,3,4), 10),
                Arguments.of(Arrays.asList(1), 1),
                Arguments.of(Arrays.asList(5, 4), 9)
        );
    }
}