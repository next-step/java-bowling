package bowling;

import bowling.domain.FrameResults;
import bowling.domain.set.FrameSets;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {

    @Test
    void singleStrikeScoreTest() {
        FrameSets frameSets = FrameSets.create();

        frameSets.play(10);

        frameSets.play(2);
        FrameResults frameResults = frameSets.play(3);

        int totalScoreToFirstFrame = frameResults.getScores().get(0);

        assertThat(totalScoreToFirstFrame).isEqualTo(15);
    }

    @Test
    void doubleStrikeScoreTest() {
        FrameSets frameSets = FrameSets.create();

        frameSets.play(10);

        frameSets.play(10);

        frameSets.play(5);
        FrameResults frameResults = frameSets.play(3);

        int totalScoreToFirstFrame = frameResults.getScores().get(0);
        int totalScoreToSecondFrame = frameResults.getScores().get(1);

        assertThat(totalScoreToFirstFrame).isEqualTo(25);
        assertThat(totalScoreToSecondFrame).isEqualTo(25 + 18);
    }

    @Test
    void singleSpareScoreTest() {
        FrameSets frameSets = FrameSets.create();

        frameSets.play(3);
        frameSets.play(7);

        FrameResults frameResults = frameSets.play(6);

        int totalScoreToFirstFrame = frameResults.getScores().get(0);

        assertThat(totalScoreToFirstFrame).isEqualTo(16);
    }

    @Test
    void doubleSpareScoreTest() {
        FrameSets frameSets = FrameSets.create();

        frameSets.play(3);
        frameSets.play(7);

        frameSets.play(5);
        frameSets.play(5);

        FrameResults frameResults = frameSets.play(3);

        int totalScoreToFirstFrame = frameResults.getScores().get(0);
        int totalScoreToSecondFrame = frameResults.getScores().get(1);

        assertThat(totalScoreToFirstFrame).isEqualTo(15);
        assertThat(totalScoreToSecondFrame).isEqualTo(15 + 13);
    }

    @Test
    void missScoreTest() {
        FrameSets frameSets = FrameSets.create();

        frameSets.play(4);
        FrameResults frameResults = frameSets.play(5);

        int totalScoreToFirstFrame = frameResults.getScores().get(0);
        assertThat(totalScoreToFirstFrame).isEqualTo(9);
    }

    @Test
    void gutterScoreTest() {
        FrameSets frameSets = FrameSets.create();

        frameSets.play(0);
        FrameResults frameResults = frameSets.play(0);

        int totalScoreToFirstFrame = frameResults.getScores().get(0);
        assertThat(totalScoreToFirstFrame).isEqualTo(0);
    }
}
