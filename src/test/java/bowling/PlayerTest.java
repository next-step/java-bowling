package bowling;

import bowling.domain.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayerTest {
    @Test
    void checkNameLength() {
        assertThatThrownBy(() -> {
            new Player("test");
        }).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("name's length must be 3");
    }
}
