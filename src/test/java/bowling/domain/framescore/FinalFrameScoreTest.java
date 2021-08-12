package bowling.domain.framescore;

import bowling.domain.frame.FinalFrameTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameScoreTest {
    @CsvSource(value = {
            "5,5=true",
            "4,5=false"
    }, delimiter = '=')
    @DisplayName("isSpare Test")
    @ParameterizedTest
    void isSpareTest(String strScores, boolean correct) {
        FrameScore frameScore = FinalFrameTest.toFinalFrameWithBowl(strScores).frameScore();

        assertThat(
                ((FinalFrameScore)frameScore).isSpare()
        ).isEqualTo(correct);
    }
}