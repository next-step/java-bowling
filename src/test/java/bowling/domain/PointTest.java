package bowling.domain;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PointTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void canGenerateValidPoint(int rawPoint) {
        Point point = new Point(rawPoint);
        assertThat(point.getClass()).isEqualTo(Point.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 11, 0x7fffffff})
    void invalidPointThrowsException(int rawPoint) {
        CustomException customException = assertThrows(CustomException.class, () -> new Point(rawPoint));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_POINT);
    }

}
