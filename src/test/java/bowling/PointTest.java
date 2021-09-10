package bowling;

import bowling.model.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PointTest {
    @Test
    public void 쓰러트린_핀_빈값() {
        assertThatThrownBy(() -> new Point(null))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new Point(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 쓰러트린_핀_숫자아님() {
        assertThatThrownBy(() -> new Point("test"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 쓰러트린_핀_범위벗어남() {
        assertThatThrownBy(() -> new Point("11"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
