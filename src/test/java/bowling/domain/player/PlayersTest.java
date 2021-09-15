package bowling.domain.player;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class PlayersTest {
    @Test
    void create() {
        Players players = new Players(Arrays.asList("AAA", "BBB", "CCC"));
        assertThat(players).isEqualTo(new Players(Arrays.asList("AAA", "BBB", "CCC")));
    }
}