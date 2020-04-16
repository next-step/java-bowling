package bowling.domain.point;

import bowling.domain.point.Point;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PointTest {
    @DisplayName("각 투구별 포인트는 0 이상 10 이하의 숫자로 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 5, 9, 10})
    void createTest(int point) {
        assertThatCode(() -> {
            new Point(point);
        }).doesNotThrowAnyException();
    }

    @DisplayName("0 미만 또는 10 초과 숫자가 들어오면 예외 발생")
    @ParameterizedTest
    @ValueSource(ints = {-10, -1, 11, 15})
    void throwExceptionWhenNotInZeroToTen(int point) {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new Point(point);
        });
    }
}