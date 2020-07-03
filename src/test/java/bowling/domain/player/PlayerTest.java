package bowling.domain.player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    void create() {
        Player player = Player.create("JJJ");
        assertThat(player.getName()).isEqualTo("JJJ");
    }

    @Test
    void create_over_length() {
        assertThatThrownBy(() ->  Player.create("JJJJ"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
