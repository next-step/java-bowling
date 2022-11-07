package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ScoreboardTest {

    @Test
    void create() {
        Scoreboard actual = new Scoreboard(new Name("abc"));

        assertThat(actual).isEqualTo(new Scoreboard(new Name("abc")));
    }
}
