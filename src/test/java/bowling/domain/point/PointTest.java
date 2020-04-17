package bowling.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PointTest {

    @Test
    @DisplayName("점수 생성 테스트")
    void createPointTest() {
        assertThatCode(
                () -> new Point(3)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("점수 생성 실패 테스트")
    void failCreatePointTest() {
        assertThatCode(
                () -> new Point(11)
        ).isInstanceOf(PointOutOfRangeException.class);
    }
}
