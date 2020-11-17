package bowling.domain.state;

import bowling.domain.point.Point;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {

    @Test
    void miss() {
        Miss miss = new Miss(Point.of(3));
        assertThat(miss.getScore()).isEqualTo("3");
    }

    @ParameterizedTest
    @ValueSource(ints = {10})
    void missAllowNumber(int point) {
        assertThatThrownBy(() -> {
            new Miss(Point.of(point));
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
