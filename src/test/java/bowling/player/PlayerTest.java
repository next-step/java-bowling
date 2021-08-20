package bowling.player;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    @Test
    void initException() throws Exception {
        Assertions.assertThatThrownBy(() -> {
            Player.init("PJSS");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
