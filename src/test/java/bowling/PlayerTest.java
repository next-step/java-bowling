package bowling;

import bowling.domain.player.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {

    @Test
    void testPlayerName() {
        assertThatIllegalArgumentException().isThrownBy(() -> Player.create("jo"));
    }

    @Test
    void testPlayerEquality() {
        assertThat(Player.create("Jihoon")).isEqualTo(Player.create("Jihoon"));
    }
}
