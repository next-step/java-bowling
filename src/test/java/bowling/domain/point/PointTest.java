package bowling.domain.point;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PointTest {

    @Test
    @DisplayName("점수 생성 테스트")
    void createPointTest() {
        assertThatCode(
                () -> Point.of(3)
        ).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("점수 생성 실패 테스트")
    void failCreatePointTest() {
        assertThatCode(
                () -> Point.of(11)
        ).isInstanceOf(PointOutOfRangeException.class);
    }
}
