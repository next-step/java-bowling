package bowling.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PointTest {

    @Test
    void createPointTest() {
        Point point = Point.inputPoint(7);
        assertThat(point.getPoint()).isEqualTo(7);
    }

    @Test
    @DisplayName("포인트가 음수일 경우 테스트")
    void createPointFailTest_음수() {
        assertThatThrownBy(() -> {
            Point.inputPoint(-1);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("포인트가 10이상일 경우 테스트")
    void createPointFailTest_10이상() {
        assertThatThrownBy(() -> {
            Point.inputPoint(11);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
