package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {

    @DisplayName("플레이어들을 생성한다")
    @Test
    void initTest() {
        Players players = Players.from(Arrays.asList(Player.from("jdh"), Player.from("pan")));
        assertThat(players.howManyPlayers()).isEqualTo(2);
    }
}
