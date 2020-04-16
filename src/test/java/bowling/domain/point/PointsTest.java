package bowling.domain.point;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class PointsTest {
    private Points points;

    @BeforeEach
    void setUp() {
        points = new Points();
    }

    @DisplayName("포인트 일급 컬렉션 생성")
    @Test
    void create() {
        assertThatCode(() -> new Points());
    }

    @DisplayName("포인트 총합 계산")
    @ParameterizedTest
    @MethodSource("points")
    void totalPoint(List<Integer> values, int totalPoint) {
        points.add(new Point(values.get(0)));
        points.add(new Point(values.get(1)));
        points.add(new Point(values.get(2)));

        assertThat(points.getTotalBonusPoint()).isEqualTo(totalPoint);
    }

    static Stream<Arguments> points() {
        return Stream.of(
                arguments(Arrays.asList(9, 2, 5), 16),
                arguments(Arrays.asList(10, 2, 4), 16),
                arguments(Arrays.asList(10, 10, 10), 30)
        );
    }
}
