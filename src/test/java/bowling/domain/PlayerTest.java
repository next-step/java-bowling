package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("초기 점수판을 출력")
    void playerHas10EmptyScore() {
        Player player = new Player("tst");
        assertThat(player.scoreBoard()).isEqualTo("| tst  |      |      |      |      |      |      |      |      |      |      |  ");
    }
}
