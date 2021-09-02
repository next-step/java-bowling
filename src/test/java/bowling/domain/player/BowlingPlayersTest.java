package bowling.domain.player;

import bowling.domain.pins.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class BowlingPlayersTest {

    private BowlingPlayers players;

    @BeforeEach
    void setup() {
        players = BowlingPlayers.of(Arrays.asList("AAA", "BBB", "CCC"));
    }

    @DisplayName("플레이어 턴이 종료되면 다음 플레이어의 턴이 된다.")
    @Test
    void player_rotate() {
        assertThat(players.getCurrentPlayer().getPlayerName()).isEqualTo("AAA");

        players.bowl(Pins.of(10));

        assertThat(players.getCurrentPlayer().getPlayerName()).isEqualTo("BBB");

        players.bowl(Pins.of(3));

        assertThat(players.getCurrentPlayer().getPlayerName()).isEqualTo("BBB");

        players.bowl(Pins.of(3));

        assertThat(players.getCurrentPlayer().getPlayerName()).isEqualTo("CCC");

        players.bowl(Pins.of(3));
        players.bowl(Pins.of(7));

        assertThat(players.getCurrentPlayer().getPlayerName()).isEqualTo("AAA");
    }

    @DisplayName("모든 플레이어의 턴이 종료되면 게임이 종료 된다.")
    @Test
    void game_end() {
        for (int i = 0; i < 36; i++) {
            players.bowl(Pins.of(10));
        }

        assertThat(players.isFinish()).isTrue();
    }
}