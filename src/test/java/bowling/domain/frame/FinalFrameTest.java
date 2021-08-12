package bowling.domain.frame;

import bowling.domain.score.TurnScoreTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class FinalFrameTest {
    @ValueSource(strings = {"0,10,10", "10,10", "4,5"})
    @DisplayName("bowl 테스트 - 스페어 또는 스트라이크에만 보너스 기회가 허용 된다.")
    @ParameterizedTest
    void bowlTest(String scores) {
        Frame frame = toFinalFrameWithBowl(scores);

        assertThat(frame.isCompleted())
                .isTrue();
    }

    public static Frame toFinalFrameWithBowl(String strScores) {
        Frame frame = Frame.firstFrame();
        for (int i = 0; i < 9; i++) {
            frame = frame.newNextFrame();
        }

        TurnScoreTest.toFrameScores(strScores)
                .forEach(frame::bowl);

        return frame;
    }
}