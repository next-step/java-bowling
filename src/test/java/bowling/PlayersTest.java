package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @Test
    @DisplayName("볼링이 끝나지 않은 플레이어 확인")
    void getContinuablePlayersTest() {
        Players players = new Players(Arrays.asList("aaa", "bbb"));

        for (Player player : players) {
            if (player.getNameString().equals("aaa")) {
                IntStream.range(0, 12).forEach(i -> player.playBowl(10));
            }

            if (player.getNameString().equals("bbb")) {
                IntStream.range(0, 10).forEach(i -> player.playBowl(10));
            }
        }

        assertThat(players.getContinuablePlayers()).containsExactly(new Player("bbb"));
    }
}
