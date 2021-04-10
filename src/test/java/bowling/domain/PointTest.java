package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @DisplayName("포인트는 0과 10 사이여야 한다.")
    @ParameterizedTest
    @ValueSource(ints = {-1,11})
    void case1(int point) {
        Assertions.assertThatThrownBy(()->{
            Point.of(point);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}