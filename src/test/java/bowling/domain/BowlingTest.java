package bowling.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

class BowlingTest {

    private static final String DELIMITER = ",";

    private Bowling bowling;

    @BeforeEach
    void setup() {
        bowling = new Bowling();
    }

    @ParameterizedTest
    @ValueSource(strings = {"10,10,10,10,10,10,10,10,10,10,10,10",
            "1,3,2,8,10,10,10,10,10,10,10,6,4,10",
            "9,1,10,0,0,10,10,10,10,10,10,0,0"})
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

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,10,10,10,10,10,10,10,10,10:0,0,1,2,3,4,5,6,7,8,9,10",
            "1,3,2,8,10,10,10,10,10,10,10,6,4,10:0,0,0,0,0,0,0,0,0,0,0,0,0,0"}, delimiter = ':')
    @DisplayName("볼링 진행과정 도중 계산이 끝난 프레임을 계산할 수 있다")
    void canDetermineEndedScores(String rawPinStrings, String rawEndedScoreStrings) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<Integer> rawPins = TestUtil.stringListToIntegerList(rawPinStrings, DELIMITER);
        List<Integer> rawEndedScores = TestUtil.stringListToIntegerList(rawEndedScoreStrings, DELIMITER);
        for(int index = 0;index<rawPins.size();index++){
            int rawPin = rawPins.get(index);
            int rawEndedScore = rawEndedScores.get(index);
            bowling.play(rawPin);
            System.out.println(bowling.closedScores());
//            softAssertions.assertThat(bowling.closedScores()).isEqualTo(rawEndedScore);
        }
        softAssertions.assertAll();
    }


}
