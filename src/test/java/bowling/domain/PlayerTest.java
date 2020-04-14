package bowling.domain;

import bowling.exception.BowlingException;
import bowling.exception.ExceptionType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    void player() {
        String name = "PES";

        Player player = new Player(name);

        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    void player_exception() {
        String name = "PESSS";

        assertThatThrownBy(() -> new Player(name))
                .isInstanceOf(BowlingException.class)
                .hasMessageContaining(ExceptionType.INVALID_NAME_LENGTH.getErrorMessage());
    }
}

