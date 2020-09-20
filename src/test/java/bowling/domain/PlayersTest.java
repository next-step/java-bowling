package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersTest {

    @Test
    @DisplayName("주어진 사람 수 만큼 플레이어 생성")
    void testCountOfPlayer() {
        // given & when
        int countOfPlayer = 3;
        Players players = new Players.Builder(() -> countOfPlayer)
                .withPlayerNames(value -> "AAA")
                .build();

        // then
        assertThat(players.size()).isEqualTo(countOfPlayer);
    }
}
