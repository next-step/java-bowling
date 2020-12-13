package bowling.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BonusScoreTest {

    Pitch strike = new Pitch();
    Pitch spare = new Pitch();

    @BeforeEach
    public void init() {
        strike.addScore(Score.perfect());
        spare.addScore(Score.of(9));
        spare.addScore(Score.of(1));
    }

    @Test
    void test_bonus_score_when_strike() {
        // Given
        BonusScore bonusFrames = new BonusScore(strike, spare);
        FrameResult currentFrameResult = FrameResult.STRIKE;

        // When
        Score bonusScore = bonusFrames.getBonusScore(currentFrameResult).get();

        // Then
        assertEquals(Score.of(19), bonusScore);
    }

    @Test
    void test_bonus_score_when_strike_not_finished() {
        // Given
        Pitch notFinishedPitch = new Pitch();
        BonusScore bonusFrames = new BonusScore(strike, notFinishedPitch);
        FrameResult currentFrameResult = FrameResult.STRIKE;

        // When
        Optional<Score> bonusScore = bonusFrames.getBonusScore(currentFrameResult);

        // Then
        assertFalse(bonusScore.isPresent());
    }

    @Test
    void test_bonus_score_when_spare() {
        // Given
        BonusScore bonusFrames = new BonusScore(strike, spare);
        FrameResult currentFrameResult = FrameResult.SPARE;

        // When
        Score bonusScore = bonusFrames.getBonusScore(currentFrameResult).get();

        // Then
        assertEquals(Score.perfect(), bonusScore);
    }

}
