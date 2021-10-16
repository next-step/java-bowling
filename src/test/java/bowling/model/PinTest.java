package bowling.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bowling.model.Pin;

public class PinTest {
    @Test
    public void 쓰러트린_핀_범위벗어남() {
        assertThatThrownBy(() -> new Pin(11))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
