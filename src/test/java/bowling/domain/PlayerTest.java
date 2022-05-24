package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    void playerHas10EmptyScore() {
        Player player = new Player("tst");
        assertThat(player.scoreBoard()).isEqualTo("| tst  |      |      |      |      |      |      |      |      |      |      |  ");
    }
}
