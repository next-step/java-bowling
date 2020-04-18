package bowling.domain.score;

import bowling.domain.frame.FrameResults;
import bowling.domain.frame.Frames;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static bowling.domain.state.PinCountTest.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ScoreTest {
    @Test
    @DisplayName("첫 투구에서 Strike가 나왔을 경우, 다음 두 투구에 대한 점수를 추가로 더한다.")
    void getScoreStrike() {
        Frames frames = new Frames();

        frames.play(pinCount10);
        frames.play(pinCount5);
        FrameResults frameResults = frames.play(pinCount1);

        int scoreFirstFrame = frameResults.getScores().get(0);

        assertThat(scoreFirstFrame).isEqualTo(16);
    }

    @Test
    @DisplayName("첫 두 투구에서 Spare가 나왔을 경우, 다음 한 투구에 대한 점수를 추가로 더한다.")
    void getScoreSpare() {
        Frames frames = new Frames();

        frames.play(pinCount5);
        frames.play(pinCount5);
        frames.play(pinCount1);
        FrameResults frameResults = frames.play(pinCount0);

        int scoreFirstFrame = frameResults.getScores().get(0);

        assertThat(scoreFirstFrame).isEqualTo(11);
    }

    @Test
    void getScoreMiss() {
        Frames frames = new Frames();

        frames.play(pinCount1);
        FrameResults frameResults = frames.play(pinCount5);

        int scoreFirstFrame = frameResults.getScores().get(0);

        assertThat(scoreFirstFrame).isEqualTo(6);
    }

    @Test
    void getScoreGutter() {
        Frames frames = new Frames();

        frames.play(pinCount0);
        FrameResults frameResults = frames.play(pinCount0);

        int scoreFirstFrame = frameResults.getScores().get(0);

        assertThat(scoreFirstFrame).isEqualTo(0);
    }
}
