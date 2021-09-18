package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    private static final int FULL_TURN = 12;

    @Test
    void isContinuedTest() {
        Players players = new Players(Arrays.asList("aaa", "bbb"));

        boolean beforeStart = players.isContinued();

        for (Player player : players) {
            bowlToEnd(player);
        }

        boolean afterFullBowl = players.isContinued();

        assertThat(beforeStart).isTrue();
        assertThat(afterFullBowl).isFalse();
    }

    private void bowlToEnd(Player player) {
        for (int i = 0; i < FULL_TURN; i++) {
            player.playBowl(10);
        }
    }
}
