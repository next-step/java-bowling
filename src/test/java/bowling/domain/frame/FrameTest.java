package bowling.domain.frame;

import bowling.domain.score.TurnScore;
import bowling.exception.BowlFailureException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

class FrameTest {
    @CsvSource({"0,10", "10,0", "5,4"})
    @DisplayName("bowl 테스트")
    @ParameterizedTest
    void bowlTest(String scores) {
        assertThatCode(() -> toFrameWithBowl(scores))
                .doesNotThrowAnyException();
    }

    @ValueSource(strings = {"0,10,10,2", "10,0,3,1", "5,4,9,4"})
    @DisplayName("bowl - 이미 두번의 투구를 진행 한경우")
    @ParameterizedTest
    void bowlFailureTest(String scores) {
        assertThatThrownBy(() -> toFrameWithBowl(scores))
                .isInstanceOf(BowlFailureException.class);
    }

    @CsvSource(value = {
            "10,0|true",
            "9,1|false",
    }, delimiter = '|')
    @DisplayName("isStrike 테스트")
    @ParameterizedTest
    void isStrikeTest(String strScores, boolean correct) {
        assertThat(toFrameWithBowl(strScores).isStrike())
                .isEqualTo(correct);
    }

    @CsvSource(value = {
            "10,0|false",
            "4,6|true",
            "1,9|true"
    }, delimiter = '|')
    @DisplayName("isSpare 테스트")
    @ParameterizedTest
    void isSpareTest(String strScores, boolean correct) {
        assertThat(toFrameWithBowl(strScores).isSpare())
                .isEqualTo(correct);
    }

    @CsvSource(value = {
            "0,0|true",
            "1,0|false",
            "0,1|false"
    }, delimiter = '|')
    @DisplayName("isMiss 테스트")
    @ParameterizedTest
    void isMissTest(String strScores, boolean correct) {
        assertThat(toFrameWithBowl(strScores).isMiss())
                .isEqualTo(correct);
    }

    private Frame toFrameWithBowl(String strScores) {
        Frame frame = new Frame();
        for (TurnScore iScore : toFrameScores(strScores)) {
            frame = frame.bowl(iScore);
        }
        return frame;
    }

    protected List<TurnScore> toFrameScores(String strScores) {
        return Arrays.stream(strScores.split(","))
                .map(Integer::parseInt)
                .map(TurnScore::of)
                .collect(Collectors.toList());
    }
}