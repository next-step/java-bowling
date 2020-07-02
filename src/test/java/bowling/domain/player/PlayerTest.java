package bowling.domain.player;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    @Test
    public void canCreate() {
        new Player("ABC");
    }
}
