package bowling.domain.point;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PointTest {

    @Test
    void create() {
        Point point = Point.of(2);
        assertThat(point.getPoint()).isEqualTo(2);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10})
    void point_negative(int point) {
        assertThatThrownBy(() -> {
            Point.of(point);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {11, 100})
    void point_exceed(int point) {
        assertThatThrownBy(() -> {
            Point.of(point);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
