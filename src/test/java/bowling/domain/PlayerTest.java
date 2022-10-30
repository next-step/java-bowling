package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void name_length_limit_test() {
        String RIGHT_NAME = "123";
        String WRONG_NAME = "1234";

        assertDoesNotThrow(() -> new Player(RIGHT_NAME));
        assertThrows(IllegalArgumentException.class, () -> new Player(WRONG_NAME));
    }

    @Test
    void getName() {
        String NAME = "OSC";
        Player player = new Player(NAME);
        assertThat(player.getName()).isEqualTo(NAME);
    }
}