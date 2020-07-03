package bowling.domain.player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    void create() {
        Player player = Player.create("JJJ", 0);
        assertThat(player.getName()).isEqualTo("JJJ");
    }

    @Test
    void create_over_length() {
        assertThatThrownBy(() ->  Player.create("JJJJ", 0))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
