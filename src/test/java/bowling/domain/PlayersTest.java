package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Players 클래스 테스트")
class PlayersTest {

    @Test
    void create() {
        String name = "ksj";
        List<String> playerNames = Arrays.asList(name, "psj");

        List<Player> players = new Players(playerNames).getPlayers();

        assertThat(players.get(0)).isEqualTo(new Player(name));
    }
}
