package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @Test
    @DisplayName("Players를 생성할 수 있다.")
    void create() {
        assertThat(Players.create()).isEqualTo(Players.create());
    }

    @Test
    @DisplayName("add를 사용하면 Player를 추가할 수 있다.")
    void add() {
        // given
        final Player player = Player.from(PlayerName.valueOf("ksd"));
        final Players players = Players.create();

        // when
        players.add(player);

        // then
        assertThat(players).isEqualTo(Players.from(Collections.singletonList(player)));
    }
}
