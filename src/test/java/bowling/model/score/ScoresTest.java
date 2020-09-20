package bowling.model.score;

import bowling.model.Result;
import bowling.model.frame.Frame;
import bowling.model.frame.Frames;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ScoresTest {
    @ParameterizedTest
    @CsvSource(value = { "10:5:5:20", "10:10:10:30", "10:3:0:13", "5:5:10:20", "9:1:5:15", "4:6:1:11" }, delimiter = ':')
    public void of(int first, int second, int third, int total) {
        Frames frames = new Frames();
        frames.addResult(first);
        frames.addResult(second);
        frames.addResult(third);

        Scores scores = frames.getScores();
        assertThat(scores.getScores().get(0)).isEqualTo(Score.of(total));
    }

    @Test
    public void of_End() {
        Frames frames = new Frames();
        for (int i = 0; i < Frame.MAX_FRAME_INDEX; i++) {
            frames.addResult(Result.MAX_PIN_COUNT);
        }
        frames.addResult(Result.MAX_PIN_COUNT);
        frames.addResult(Result.MAX_PIN_COUNT);

        Scores scores = frames.getScores();
        assertThat(scores.getScores().get(Frame.MAX_FRAME_INDEX - 1)).isEqualTo(Score.of(30));
    }
}
