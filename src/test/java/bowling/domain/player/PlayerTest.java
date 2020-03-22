package bowling.domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    @DisplayName("플레이어 객체 비교")
    void compareToPlayer() {
        // give
        Player actualPlayer = new Player("KSJ");
        Player expectedPlayer = new Player("KSJ");
        // when
        boolean same = actualPlayer.equals(expectedPlayer);
        // then
        assertThat(same).isTrue();
    }
}
