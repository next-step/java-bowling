package bowling.domain.frame;

import bowling.domain.TestUtil;
import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FramesTest {

    private static final String DELIMITER = ",";

    private Frames frames = new Frames();

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,10,10,10,10,10,10,10,10,10",
            "1,3,2,8,10,10,10,10,10,10,10,6,4,10",
            "9,1,10,0,0,10,10,10,10,10,10,0,0"}, delimiter = ':')
    @DisplayName("볼링을 끝까지 진행할 수 있다")
    void canBowlToEnd(String rawPinStrings) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<Integer> rawPins = TestUtil.stringListToIntegerList(rawPinStrings, DELIMITER);
        for (Integer rawPin : rawPins) {
            softAssertions.assertThat(frames.isEnd()).isFalse();
            frames.moveFrameIfNeeded();
            softAssertions.assertThat(frames.isEnd()).isFalse();
            frames.bowl(rawPin);
        }
        softAssertions.assertThat(frames.isEnd()).isTrue();
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,10,10,10,10,10,10,10,10,10,10",
            "1,3,2,8,10,10,10,10,10,10,10,6,4,10,8",
            "9,1,10,0,0,10,10,10,10,10,10,0,0,0"}, delimiter = ':')
    @DisplayName("정해진 수 보다 더 많이 던질 수는 없다")
    void tooMuchBowlThrowsException(String rawPinStrings) {
        List<Integer> rawPins = TestUtil.stringListToIntegerList(rawPinStrings, DELIMITER);
        for (int index = 0; index < rawPins.size() - 1; index++) {
            int rawPin = rawPins.get(index);
            frames.moveFrameIfNeeded();
            frames.bowl(rawPin);
        }
        int errorPin = rawPins.get(rawPins.size() - 1);
        CustomException customException =
                assertThrows(CustomException.class, () -> frames.bowl(errorPin));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_BOWL);
    }

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,10,10,10,10,10,10,10,10,10:1,2,3,4,5,6,7,8,9,10,10,10",
            "1,3,2,8,10,10,10,10,10,10,10,6,4,10:1,1,2,2,3,4,5,6,7,8,9,10,10,10,10",
            "9,1,10,0,0,10,10,10,10,10,10,0,0:1,1,2,3,3,4,5,6,7,8,9,10,10"}, delimiter = ':')
    @DisplayName("볼링 프레임 수를 정확하게 알려 줄 수 있다")
    void canDetermineFrameCount(String rawPinStrings, String frameCountStrings) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<Integer> rawPins = TestUtil.stringListToIntegerList(rawPinStrings, DELIMITER);
        List<Integer> frameCounts = TestUtil.stringListToIntegerList(frameCountStrings, DELIMITER);
        for (int index = 0; index < rawPins.size(); index++) {
            int rawPin = rawPins.get(index);
            int frameCount = frameCounts.get(index);
            softAssertions.assertThat(frames.frameCount()).isEqualTo(frameCount);
            frames.moveFrameIfNeeded();
            frames.bowl(rawPin);
        }
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @CsvSource(value = {"10,10,10,10,10,10,10,10,10,10,10,10:2,2,2,2,2,2,2,2,2,0,0,0",
            "1,3,2,8,10,10,10,10,10,10,10,6,4,10:0,0,0,1,2,2,2,2,2,2,2,0,0,0",
            "9,1,10,0,0,10,10,10,10,10,10,0,0:0,1,2,0,0,2,2,2,2,2,2,0,0"}, delimiter = ':')
    @DisplayName("볼링의 보너스 프레임 수를 정확하게 알려 줄 수 있다")
    void canDetermineBonusCount(String rawPinStrings, String rawBonusCountStrings) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<Integer> rawPins = TestUtil.stringListToIntegerList(rawPinStrings, DELIMITER);
        List<Integer> rawBonuses = TestUtil.stringListToIntegerList(rawBonusCountStrings, DELIMITER);
        for (int index = 0; index < rawPins.size(); index++) {
            int rawPin = rawPins.get(index);
            int rawBonus = rawBonuses.get(index);
            frames.moveFrameIfNeeded();
            frames.bowl(rawPin);
            softAssertions.assertThat(frames.currentFrameBonus()).isEqualTo(rawBonus);
        }
        softAssertions.assertAll();
    }

}
