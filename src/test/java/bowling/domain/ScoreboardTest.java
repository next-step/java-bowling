package bowling.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ScoreboardTest {

    @Test
    void create() {
        Scoreboard actual = new Scoreboard(new Name("abc"));

        assertThat(actual).isEqualTo(new Scoreboard(new Name("abc")));
    }

    @Test
    void name() {
        assertThat(new Scoreboard(new Name("cys")).name()).isEqualTo(new Name("cys"));
    }
}
