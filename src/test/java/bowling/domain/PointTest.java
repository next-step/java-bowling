package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @DisplayName("포인트 생성")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    void create(int point) {
        assertThatCode(() -> new Point(point));
    }

    @DisplayName("10점 이상이거나 음수일 경우 포인트 생성 실패")
    @ParameterizedTest
    @ValueSource(ints = {-1, 11})
    void createFailByInvalidPoint(int point) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Point(point));
    }
}