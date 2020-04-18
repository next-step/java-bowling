package bowling.domain.score;

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
        frames.play(pinCount1);

        int scoreFirstFrame = frames.getScores().get(0);
        int scoreSecondFrame = frames.getScores().get(1);

        assertThat(scoreFirstFrame).isEqualTo(16);
        assertThat(scoreSecondFrame).isEqualTo(6);
    }
}
