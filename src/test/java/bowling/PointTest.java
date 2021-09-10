package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PointTest {
    @Test
    public void 쓰러트린_핀_빈값() {
        Assertions.assertThatThrownBy(() -> new Point(null))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> new Point(""))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 쓰러트린_핀_숫자아님() {
        Assertions.assertThatThrownBy(() -> new Point("test"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void 쓰러트린_핀_범위벗어남() {
        Assertions.assertThatThrownBy(() -> new Point("11"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
