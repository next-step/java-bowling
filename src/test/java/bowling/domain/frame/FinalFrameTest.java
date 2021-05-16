package bowling.domain.frame;

import bowling.domain.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private static final String DELIMITER = ",";
    private FinalFrame finalFrame;

    @BeforeEach
    void setup() {
        finalFrame = new FinalFrame();
    }

    @ParameterizedTest
    @CsvSource(value = {"10:0", "10,10:0", "10,9:0", "9,1:0", "0,10:0",
            "3,7:0", "5,5:0", "10,10,10:0", "10,9,1:0", "9,0:0",
            "0,3:0", "2,7:0", "5,0:0"}, delimiter = ':')
    @DisplayName("보너스를 확인할 수 있다")
    void canDetermineStrikeBonusStatus(String rawScoreStrings, int bonus) {
        List<Integer> rawScores = TestUtil.stringListToIntegerList(rawScoreStrings, DELIMITER);
        for (Integer rawScore : rawScores) {
            finalFrame.bowl(rawScore);
        }
        assertThat(finalFrame.bonusAmount()).isEqualTo(bonus);
    }

}
