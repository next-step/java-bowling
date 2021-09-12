package bowling;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PointTest {

    @Test
    public void create_test() {
        Point point = new Point(10);
        assertThat(point).isEqualTo(point);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    public void invalid_point_test(int number) {
        assertThatThrownBy(() -> new Point(number)).isInstanceOf(IllegalArgumentException.class);
    }
}
