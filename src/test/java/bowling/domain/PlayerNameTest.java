package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerNameTest {

    @Test
    void testTooLongName() {
        Assertions.assertThatThrownBy(() -> new PlayerName("jackson"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("player name length must be 3");
    }
}
