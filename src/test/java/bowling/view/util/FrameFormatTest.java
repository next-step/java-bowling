package bowling.view.util;

import bowling.domain.frame.FrameTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FrameFormatTest {
    @CsvSource(value = {
            "10=X",
            "5=5",
            "0,10=-|/",
            "4,5=4|5"
    }, delimiter = '=')
    @DisplayName("format 테스트")
    @ParameterizedTest
    void formatTest(String strScores, String correctFormat) {
        assertThat(
                new FrameFormat(
                        FrameTest.toFrameWithBowl(strScores).frameScore()
                ).format()
        ).isEqualTo(correctFormat);
    }
}