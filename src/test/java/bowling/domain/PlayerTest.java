package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp(){
        player = new Player("NKE");
    }

    @Test
    void bowlTest() {
        player.bowl(10);
        assertThat(player.isNthFrameOver(1)).isTrue();
    }
}
