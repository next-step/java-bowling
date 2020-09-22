package bowling.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {
    @Test
    void create() {
        BowlingGame bowlingGame = new BowlingGame("PJS");

        assertThat(bowlingGame).isEqualTo(new BowlingGame("PJS"));
    }
}
