package bowling.domain.point;

import bowling.exception.ScorePointRangeOutBoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    @DisplayName("포인트는 마이너스를 가질수는 없다.")
    void throwNegativeException() {
        assertThatThrownBy(() -> Point.valueOf(-1))
                .isInstanceOf(ScorePointRangeOutBoundException.class);
    }

    @Test
    @DisplayName("포인트는 10을 넘을수는 없다.")
    void throwOverException() {
        assertThatThrownBy(() -> Point.valueOf(11))
                .isInstanceOf(ScorePointRangeOutBoundException.class);
    }

}