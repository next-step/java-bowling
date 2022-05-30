package bowling.domain;

import antlr.collections.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    @Test
    @DisplayName("플레이어의 이름 출력")
    public void getName() {
        assertThat(new Player("chk").name()).isEqualTo(new PlayerName("chk"));
    }

    @Test
    void getSubtotals() {
        assertThat(new Player("chk").subtotals()).first().isInstanceOf(Subtotal.class);
    }

    @Test
    void getScores() {
        assertThat(new Player("chk").scores()).first().isInstanceOf(Score.class);
    }
}
