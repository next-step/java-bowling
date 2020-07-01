package bowling.domain.player;

import bowling.domain.player.Player;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void canCreate() {
        Player player = new Player("ABC");
    }
}
