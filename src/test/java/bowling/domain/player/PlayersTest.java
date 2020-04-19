package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {

    @DisplayName("Players 객체를 생성할 수 있다.")
    @Test
    void create() {
        List<Player> players = new ArrayList<>();
        players.add(Player.of("o"));
        players.add(Player.of("t"));
        players.add(Player.of("k"));

        Players actual = new Players(players);

        assertThat(actual.getPlayers()).hasSize(3);
    }
}