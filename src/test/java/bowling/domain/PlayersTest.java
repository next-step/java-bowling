package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * author       : gwonbyeong-yun <sksggg123>
 * ------------------------------------------
 * | email        : sksggg123               |
 * | github       : github.com/sksggg123    |
 * | blog         : sksggg123.github.io     |
 * ------------------------------------------
 * project      : java-bowling
 * create date  : 2019-07-20 03:16
 */
public class PlayersTest {
    private List<Player> players;

    @BeforeEach
    void 초기화() {
        players = new ArrayList<>(
                Arrays.asList(
                        Player.of("KBY"),
                        Player.of("KKK"),
                        Player.of("BBB"),
                        Player.of("YYY")));
    }

    @DisplayName("플레이어 수 가지고오기")
    @Test
    void 플레이어_숫자() {
        Players player = new Players(players);
        assertThat(player.count()).isEqualTo(4);
    }
}
