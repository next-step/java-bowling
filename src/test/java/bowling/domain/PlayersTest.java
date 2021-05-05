package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {
    @DisplayName("참가자들 클래스를 정상적으로 생성한다.")
    @Test
    void create() {
        Player player1 = new Player("abc");
        Player player2 = new Player("def");

        Players players = new Players(Arrays.asList(player1, player2));

        assertThat(players).isEqualTo(new Players(Arrays.asList(player1, player2)));
    }
}
