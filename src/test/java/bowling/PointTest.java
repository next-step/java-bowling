package bowling;

import bowling.model.Point;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class PointTest {
    @Test
    public void 쓰러트린_핀_빈값() {
        assertThatThrownBy(() -> Point.isValid(null))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> Point.isValid(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 쓰러트린_핀_숫자아님() {
        assertThatThrownBy(() -> Point.isValid("test"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 쓰러트린_핀_범위벗어남() {
        assertThatThrownBy(() -> Point.isValid("11"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
