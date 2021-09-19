package bowling.domain.player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    void create() {
        Player player = new Player("KAJ");
        assertThat(player).isEqualTo(new Player("KAJ"));
    }

    @Test
    void isFinish() {
        Player player = new Player("KAJ");
        assertThat(player.finish()).isFalse();
    }

    @Test
    void nextFrameNo() {
        Player player = new Player("KAJ");
        assertThat(player.nextFrameNo()).isEqualTo(1);
    }
}