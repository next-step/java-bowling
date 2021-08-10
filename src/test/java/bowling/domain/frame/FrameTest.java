package bowling.domain.frame;

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

    @CsvSource(value = {
            "10|STRIKE", // 스트라이크는 다음턴이 자동으로 생략 된다.
            "9,1|SPARE",
            "4,5|NORMAL",
            "0,0|MISS"
    }, delimiter = '|')
    @DisplayName("frameScore 테스트")
    @ParameterizedTest
    void isStrikeTest(String strScores, String correctFrameScoreName) {
        FrameScoreGrade correct = FrameScoreGrade.valueOf(correctFrameScoreName);

        assertThat(toFrameWithBowl(strScores).frameScoreGrade())
                .isEqualTo(correct);
    }

    @CsvSource(value = {
            "10|1",
            "1,0|2",
            "0,1|2"
    }, delimiter = '|')
    @ParameterizedTest
    void scoresTest(String strScores, int correctSize) {
        assertThat(
                toFrameWithBowl(strScores).scores().size()
        ).isEqualTo(correctSize);
    }

    public static Frame toFrameWithBowl(String strScores) {
        Frame frame = new Frame(1);

        TurnScoreTest.toFrameScores(strScores)
                .forEach(frame::bowl);
        return frame;
    }
}