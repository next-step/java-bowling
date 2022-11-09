package bowling;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingScoreRecordTest {

    @Test
    void hitMiss() {
        BowlingScoreRecord bowlingScoreRecord = new BowlingScoreRecord();
        int frameNumber = bowlingScoreRecord.hit(0, 5);
        Assertions.assertThat(frameNumber).isEqualTo(0);
    }

    @Test
    void hitStrike() {
        BowlingScoreRecord bowlingScoreRecord = new BowlingScoreRecord();
        int frameNumber = bowlingScoreRecord.hit(0, 10);
        Assertions.assertThat(frameNumber).isEqualTo(1);
    }

    @Test
    void hitSpare() {
        BowlingScoreRecord bowlingScoreRecord = new BowlingScoreRecord();
        int frameNumber = bowlingScoreRecord.hit(0, 5);
        frameNumber = bowlingScoreRecord.hit(frameNumber, 5);
        Assertions.assertThat(frameNumber).isEqualTo(1);
    }

    @Test
    void hitGutterTwice() {
        BowlingScoreRecord bowlingScoreRecord = new BowlingScoreRecord();
        int frameNumber = bowlingScoreRecord.hit(0, 0);
        frameNumber = bowlingScoreRecord.hit(frameNumber, 0);
        Assertions.assertThat(frameNumber).isEqualTo(1);
    }
}