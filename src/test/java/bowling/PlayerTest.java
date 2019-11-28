package bowling;

import bowling.domain.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    @Test
    @DisplayName("객체 비교")
    void compareWithPlayerObject() {
        Player player = new Player("KSJ");
        Player player1 = new Player("KSJ");

        assertThat(player).isEqualTo(player1);
    }
}
