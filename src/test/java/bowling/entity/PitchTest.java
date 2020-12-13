package bowling.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PitchTest {

    Pitch pitch;

    @BeforeEach
    public void init() {
        pitch = new Pitch();
    }

    @Test
    void test_strike() {
        pitch.addScore(Score.perfect());
        assertEquals(FrameResult.STRIKE, pitch.getFrameResult());
    }

    @Test
    void test_spare() {
        pitch.addScore(Score.of(5));
        pitch.addScore(Score.of(5));
        assertEquals(FrameResult.SPARE, pitch.getFrameResult());
    }

    @Test
    void test_gutter() {
        pitch.addScore(Score.zero());
        pitch.addScore(Score.zero());
        assertEquals(FrameResult.GUTTER, pitch.getFrameResult());
    }

    @Test
    void test_open() {
        pitch.addScore(Score.of(2));
        pitch.addScore(Score.of(3));
        assertEquals(FrameResult.MISS, pitch.getFrameResult());
    }

}