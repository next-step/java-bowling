package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
    @Test
    void of() {
        assertThatCode(() -> Player.of("SKT"))
                .doesNotThrowAnyException();
    }

    @Test
    void ofException() {
        assertThatThrownBy(() -> Player.of("tester"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void getDto() {
        Player player = Player.of("KTH");

        assertThat(player.getDto().getName())
                .isEqualTo("KTH");

        assertThat(player.getDto().getFrames().getFrames())
                .hasSize(1);

        player.shot(10);
        player.shot(10);
        assertThat(player.getDto().getFrames().getFrames())
                .hasSize(2);
    }

}