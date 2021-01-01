package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {
    private Player player;

    @BeforeEach
    void setUp() {
        player = new Player("abc");
    }

    @Test
    void bowlTest_StrikeOnFirstFrame() {
        player.bowl(10);
        assertThat(player.isNthFrameOver(1)).isTrue();
    }

    @Test
    void bowlTest_StrikeAndBowledOnce() {
        player.bowl(10);
        player.bowl(5);
        assertThat(player.isNthFrameOver(2)).isFalse();
    }
}
