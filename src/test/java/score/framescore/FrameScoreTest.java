package score.framescore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FrameScoreTest {

    @ParameterizedTest
    @CsvSource(value = {"0,false", "1,true"})
    void canAdd(int count, boolean answer) {
        FrameScore frameScore = new FrameScore(1, count);
        assertThat(frameScore.canAdd()).isEqualTo(answer);
    }

    @Test
    void addScore() {
        FrameScore frameScore = new FrameScore(1, 1);
        FrameScore addedFrameScore = frameScore.addScore(1);

        assertThat(addedFrameScore).isEqualTo(new FrameScore(2, 0));
    }

    @ParameterizedTest
    @CsvSource(value = {"1,1,''", "1,0,1"})
    void getCalculated(int score, int count, String answer) {
        FrameScore frameScore = new FrameScore(score, count);

        assertThat(frameScore.getCalculated(score)).isEqualTo(answer);
    }

    @Test
    void getSumScore() {
        FrameScore frameScore = new FrameScore(1, 1);
        assertThat(frameScore.getSumScore(1)).isEqualTo(2);
    }
}