package bowling.domain.frame;

import bowling.domain.score.Score;
import bowling.domain.score.TurnScoreTest;
import bowling.exception.BowlFailureException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FrameTest {
    @ValueSource(strings = {"0,10", "10", "5,4"})
    @DisplayName("bowl 테스트")
    @ParameterizedTest
    void bowlTest(String scores) {
        assertThat(toFrameWithBowl(scores).isCompleted())
                .isTrue();
    }

    @ValueSource(strings = {"0,10,10,2", "10,0,3,1", "5,4,9,4"})
    @DisplayName("bowl - 이미 두번의 투구를 진행 한경우")
    @ParameterizedTest
    void bowlFailureTest(String scores) {
        assertThatThrownBy(() -> toFrameWithBowl(scores))
                .isInstanceOf(BowlFailureException.class);
    }

    @ValueSource(strings = {
            "5,5",
            "10"
    })
    @DisplayName("스트라이크나 스페어 일 때 다음 프레임의 정보가 없다면 frameScore()의 결과는 Empty이여야 한다.")
    @ParameterizedTest
    void frameScoreEmptyTest(String strScores) {
        Frame frame = toFrameWithBowl(strScores);

        assertThat(frame.frameScore().isEmpty())
                .isTrue();
    }

    @CsvSource(value = {
            "5,4=9",
            "0,1=1",
            "0,0=0"
    }, delimiter = '=')
    @DisplayName("스트라이크나 스페어가 아니라면 즉시 계산 되어야 한다.")
    @ParameterizedTest
    void frameScoreNormalTest(String strScores, int correct) {
        Frame frame = toFrameWithBowl(strScores);

        assertThat(frame.frameScore())
                .isEqualTo(new Score(correct));
    }

    @CsvSource(value = {
            "10|1",
            "1,0|2",
            "0,1|2"
    }, delimiter = '|')
    @ParameterizedTest
    void scoresTest(String strScores, int correctSize) {
        assertThat(
                toFrameWithBowl(strScores).turnScores().size()
        ).isEqualTo(correctSize);
    }

    public static Frame toFrameWithBowl(String strScores) {
        Frame frame = Frame.firstFrame();

        TurnScoreTest.toFrameScores(strScores)
                .forEach(frame::bowl);
        return frame;
    }
}