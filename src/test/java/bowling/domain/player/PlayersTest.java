package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    @DisplayName("Players를 생성할 수 있다.")
    void create() {
        assertThat(Players.create()).isEqualTo(Players.create());
    }
}
