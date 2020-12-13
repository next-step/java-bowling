package bowling.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    Frame frame;

    @BeforeEach
    public void init() {
        frame = new Frame(1);
    }

    @Test
    void test_frame_strike() {
        frame.bowl(Score.perfect());
        assertEquals(FrameResult.STRIKE, frame.getFrameResult());
    }

    @Test
    void test_frame_spare() {
        frame.bowl(Score.of(8));
        frame.bowl(Score.of(2));
        assertEquals(FrameResult.SPARE, frame.getFrameResult());
    }

    @Test
    void test_frame_gutter() {
        frame.bowl(Score.zero());
        frame.bowl(Score.zero());
        assertEquals(FrameResult.GUTTER, frame.getFrameResult());
    }

    @Test
    void test_frame_open() {
        frame.bowl(Score.of(8));
        frame.bowl(Score.of(1));
        assertEquals(FrameResult.MISS, frame.getFrameResult());
    }

    @Test
    void test_total_score_strike() {
        // Given
        Pitch strike = Pitch.strike();
        Pitch spare = Pitch.of(Score.of(9), Score.of(1));
        BonusScore bonusScore = new BonusScore(strike, spare);
        Frame frame = new Frame(1);
        frame.bowl(Score.perfect());

        // When
        Score totalScore = frame.getTotalScore(bonusScore).get();

        // Then
        assertEquals(Score.of(29), totalScore);
    }

    @Test
    void test_total_score_not_finished() {
        // Given
        Pitch strike = Pitch.strike();
        Pitch notFinishedPitch = new Pitch();
        BonusScore bonusScore = new BonusScore(strike, notFinishedPitch);
        Frame frame = new Frame(1);
        frame.bowl(Score.perfect());

        // When
        Optional<Score> totalScore = frame.getTotalScore(bonusScore);

        // Then
        assertFalse(totalScore.isPresent());
    }

}