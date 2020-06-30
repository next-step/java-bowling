package camp.nextstep.edu.nextstep8.bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FrameTest {
    @DisplayName("프레임 별 결과 심볼이 잘 생성 되는지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "10:0:X",
            "9:1:9|/",
            "1:2:1|2",
            "0:0:-"
    }, delimiter = ':')
    public void getResultSymbolTest(int score, int spare, String expected) {
        // given
        Frame frame = new Frame(score);
        frame.updateSpare(spare);

        // when
        String result = frame.getResultSymbol();

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("Strike 이거나 Spare 인지 확인")
    @ParameterizedTest
    @CsvSource(value = {
            "10:0:true",
            "9:1:true",
            "8:1:false",
            "0:0:false"
    }, delimiter = ':')
    public void isStrikeOrSpare(int score, int spare, boolean expected) {
        // given
        Frame frame = new Frame(score);
        frame.updateSpare(spare);

        // when
        boolean result = frame.isStrikeOrSpare();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
