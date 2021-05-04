package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

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

    @Test
    @DisplayName("value를 사용하면 player List를 반환한다.")
    void value() {
        // given
        final List<Player> playerList = Collections.singletonList(Player.from(PlayerName.valueOf("ksd")));
        final Players players = Players.from(playerList);

        // when
        final List<Player> actualPlayerList = players.value();

        // then
        assertThat(actualPlayerList).isEqualTo(playerList);
    }
}
