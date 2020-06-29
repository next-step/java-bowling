package bowling.domain.player;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PlayerTest {

    @Test
    void createPlayerTest() {
        String pgj = "PGJ";
        Player player = Player.create(pgj);
        assertThat(player.toString()).isEqualTo("PGJ");
    }

    @Test
    void createPlayerFailTest() {
        assertThatThrownBy(() -> {
            Player.create("PGJJJ");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
