package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    void createPlayer() {
        Player player = new Player("JHJ");

        assertThat(player).isEqualTo(new Player("JHJ"));
    }

    @Test
    void playerNameExceptionTest() {
        assertThatThrownBy(() -> {
            new Player("JANG");
        }).isInstanceOf(IllegalArgumentException.class);
    }

}