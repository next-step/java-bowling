package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class BowlingScoreRecordTest {

    @Test
    void hitMiss() {
        BowlingGame bowlingScoreRecord = new BowlingGame();
        FrameNumber frameNumber = bowlingScoreRecord.pitchingBall(5);
        Assertions.assertThat(frameNumber).isEqualTo(FrameNumber.first());
    }

    @Test
    void hitStrike() {
        BowlingGame bowlingScoreRecord = new BowlingGame();
        FrameNumber frameNumber = bowlingScoreRecord.pitchingBall( 10);
        Assertions.assertThat(frameNumber).isEqualTo(FrameNumber.number(2));
    }

    @Test
    void hitSpare() {
        BowlingGame bowlingScoreRecord = new BowlingGame();
        bowlingScoreRecord.pitchingBall(5);
        FrameNumber frameNumber = bowlingScoreRecord.pitchingBall(5);
        Assertions.assertThat(frameNumber).isEqualTo(FrameNumber.number(2));
    }

    @Test
    void hitGutterTwice() {
        BowlingGame bowlingScoreRecord = new BowlingGame();
        bowlingScoreRecord.pitchingBall(0);
        FrameNumber frameNumber = bowlingScoreRecord.pitchingBall(0);
        Assertions.assertThat(frameNumber).isEqualTo(FrameNumber.number(2));
    }
}