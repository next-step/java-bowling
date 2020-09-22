package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame("PJS");
    }

    @Test
    void create() {
        assertThat(bowlingGame).isEqualTo(new BowlingGame("PJS"));
    }

    @ParameterizedTest
    @CsvSource(value = {"10,1,9,10,3", "1,1,1,1,2", "10,10,10,10,4"})
    void pitch(int first, int second, int third, int fourth, int expect) {
        bowlingGame.pitch(first);
        bowlingGame.pitch(second);
        bowlingGame.pitch(third);
        bowlingGame.pitch(fourth);

        assertThat(bowlingGame.getFrames()).hasSize(expect);
    }
}
