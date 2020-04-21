package bowling.domain.player;

import bowling.domain.pin.BowlCount;
import bowling.domain.pin.Pins;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {
    private Players players;

    @BeforeEach
    void setUp() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(Player.of("o"));
        playerList.add(Player.of("t"));
        playerList.add(Player.of("k"));
        players = new Players(playerList);
    }

    @DisplayName("Players 객체를 생성할 수 있다.")
    @Test
    void create() {
        assertThat(players.getPlayers()).hasSize(3);
    }

    @DisplayName("Player들이 게임을 진행하고있는지 확인할 수 있다.")
    @Test
    void isPlaying() {
        assertThat(players.isPlaying()).isTrue();
    }

    @DisplayName("Player들 모두가 게임이 종료됨을 확인할 수 있다.")
    @Test
    void isAllEnd() {
        for (Player player : players.getPlayers()) {
            for (int i = 0; i < 12; i++) {
                player.bowl(Pins.of().knockOver(new BowlCount(10)));
                player.waitNextFrame();
            }
        }
        assertThat(players.isPlaying()).isFalse();
    }
}