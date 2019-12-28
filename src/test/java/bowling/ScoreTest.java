package bowling;

import bowling.domain.FrameResults;
import bowling.domain.set.FrameSets;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    void strikeScoreTest() {
        FrameSets frameSets = FrameSets.create();

        frameSets.play(10);

        frameSets.play(2);
        FrameResults frameResults = frameSets.play(3);

        int firstStrikeScore = frameResults.getScores().get(0);

        assertThat(firstStrikeScore).isEqualTo(15);
    }

    @Test
    void spareScoreTest() {
        FrameSets frameSets = FrameSets.create();

        frameSets.play(3);
        frameSets.play(7);

        FrameResults frameResults = frameSets.play(6);

        int firstSpareScore = frameResults.getScores().get(0);
        
        assertThat(firstSpareScore).isEqualTo(16);
    }
}
