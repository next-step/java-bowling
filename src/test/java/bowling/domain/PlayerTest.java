package bowling.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class PlayerTest {

    @Test
    void shouldValidatePlayer() {
        assertThrowsExactly(IllegalArgumentException.class, () -> new Player(null, null));
    }

    @Test
    void shouldReturnResultWithUsername() {
        Player player = new Player(new Username("kcs"), new Bowling());

        ScoreResult result = player.bowl(10);

        assertThat(result.isSameUsername(new Username("kcs"))).isTrue();
    }

}
