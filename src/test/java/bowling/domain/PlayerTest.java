package bowling.domain;

import bowling.domain.player.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class PlayerTest {
    @Test
    public void setPersonName() {
        String playerName = "player1";
        Player player = Player.getInstance(playerName);

        assertThat(player.getName()).isEqualTo(playerName);
    }
}
