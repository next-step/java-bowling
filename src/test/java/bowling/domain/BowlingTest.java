package bowling.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class BowlingTest {

    private static final String DELIMITER = ",";

    private Bowling bowling;

    @BeforeEach
    void setup() {
        bowling = new Bowling();
    }

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,10,10,10,10,10,10,10,10,10",
            "1,3,2,8,10,10,10,10,10,10,10,6,4,10",
            "9,1,10,0,0,10,10,10,10,10,10,0,0"}, delimiter = ':')
    @DisplayName("볼링의 전 과정을 진행할 수 있다")
    void canPlayBowling(String rawPinStrings) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<Integer> rawPins = TestUtil.stringListToIntegerList(rawPinStrings, DELIMITER);
        for (Integer rawPin : rawPins) {
            softAssertions.assertThat(bowling.isEnd()).isFalse();
            bowling.play(rawPin);
        }
        softAssertions.assertThat(bowling.isEnd()).isTrue();
        softAssertions.assertAll();
    }

}
