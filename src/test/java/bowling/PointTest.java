package bowling;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bowling.model.Point;

public class PointTest {
    @Test
    public void 쓰러트린_핀_범위벗어남() {
        assertThatThrownBy(() -> new Point(11))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
