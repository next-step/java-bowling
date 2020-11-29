package bowling.domain.frame;

import bowling.domain.score.InvalidScoreAddException;
import bowling.domain.score.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("일반 프레임 테스트")
public class FrameTest {
    @DisplayName("점수 기록")
    @Test
    public void recordScore() {
        Frame frame = Frame.empty();

        frame.record(10);

        assertThat(frame.getScores().get(0)).isEqualTo(Score.strike());
    }

    @DisplayName("점수기록하면 안되는 경우")
    @Test
    public void invalidRecordScore() {
        Frame frame = Frame.empty();
        frame.record(10);

        assertThat(frame.isFinished()).isEqualTo(true);
        assertThatThrownBy(() -> {
            frame.record(10);
        }).isInstanceOf(InvalidScoreAddException.class);
    }
}