package domain;

import domain.frame.Frames;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    private BowlingGame bowlingGame;

    @BeforeEach
    void setUp() {
        bowlingGame = new BowlingGame();
    }

    @Test
    void bowl() {
        Frames bowl = bowlingGame.bowl(Pins.ALL);
        assertThat(bowl.isFinished()).isFalse();
    }

    @Test
    void isRunning() {
        bowlingGame.bowl(Pins.ALL);
        bowlingGame.bowl(Pins.ALL);
        bowlingGame.bowl(Pins.ALL);
        bowlingGame.bowl(Pins.ALL);
        bowlingGame.bowl(Pins.ALL);
        bowlingGame.bowl(Pins.ALL);
        bowlingGame.bowl(Pins.ALL);
        bowlingGame.bowl(Pins.ALL);
        bowlingGame.bowl(Pins.ALL);
        bowlingGame.bowl(Pins.ALL);
        bowlingGame.bowl(Pins.ALL);
        Frames bowl = bowlingGame.bowl(Pins.ALL);
        assertThat(bowl.isFinished()).isTrue();
    }
}