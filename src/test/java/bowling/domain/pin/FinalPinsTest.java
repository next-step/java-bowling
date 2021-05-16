package bowling.domain.pin;

import bowling.domain.TestUtil;
import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FinalPinsTest {

    private static final String DELIMITER = ",";

    private FinalPins finalPins;

    @BeforeEach
    void setup() {
        finalPins = new FinalPins();
    }

    @ParameterizedTest
    @CsvSource(value = {"10:true", "10,10:true", "10,10,10:true", "0,10:false", "9:false",
            "8:2:10:true", "8:false", "7:false", "6:false", "5:false",
            "4:false", "1:false", "3:false", "0:false"}, delimiter = ':')
    @DisplayName("스트라이크를 판단할 수 있다")
    void canDetermineStrike(String rawPinStrings, boolean expected) {
        List<Integer> rawPins = TestUtil.stringListToIntegerList(rawPinStrings, DELIMITER);
        for (int index = 0; index < rawPins.size() - 1; index++) {
            finalPins.bowl(rawPins.get(index));
        }
        finalPins.bowl(rawPins.get(rawPins.size() - 1));
        assertThat(finalPins.isStrike()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"10,0,10:true", "10,1,9:true", "10,2,8:true", "10,5,5:true", "10,6,4:true",
            "10,0:false", "9,1:true", "8,2:true", "7,3:true", "6,4:true",
            "5,5:true", "4,3:false", "8,1:false", "5,3:false", "0,2:false"}, delimiter = ':')
    @DisplayName("스페어처리를 판단할 수 있다")
    void canDetermineSpare(String rawPinStrings, boolean expected) {
        List<Integer> rawPins = TestUtil.stringListToIntegerList(rawPinStrings, DELIMITER);
        for (int index = 0; index < rawPins.size() - 1; index++) {
            finalPins.bowl(rawPins.get(index));
        }
        finalPins.bowl(rawPins.get(rawPins.size() - 1));
        assertThat(finalPins.isSpare()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"9,2", "8,3", "7,5", "6,10", "5,6",
            "4,8", "8,7", "5,9", "10,2,9", "10,8,3", "10,7,5",
            "10,6,10", "10,5,6", "10,4,8", "10,8,7", "10,5,9"})
    @DisplayName("두번째에서 남은 핀보다 더 많은 핀을 쓰러뜨리려고 하면 INVALID_SECOND_PIN을 던진다")
    void bowlOverflowThrowsException(String rawPinStrings) {
        List<Integer> rawPins = TestUtil.stringListToIntegerList(rawPinStrings, ",");
        for (int index = 0; index < rawPins.size() - 1; index++) {
            finalPins.bowl(rawPins.get(index));
        }
        int errorPin = rawPins.get(rawPins.size() - 1);
        CustomException customException = assertThrows(CustomException.class, () -> finalPins.bowl(errorPin));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_SECOND_PIN);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,10,3", "10,10,10", "10,0,10", "8,2,3", "0,0", "6,4,10"})
    @DisplayName("정해진 횟수보다 더 많이 던지면 INVALID_BOWL을 던진다")
    void canBowlFinalFrame(String rawPins) {
        SoftAssertions softAssertions = new SoftAssertions();
        List<Integer> integers = Arrays.stream(rawPins.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        for (Integer integer : integers) {
            softAssertions.assertThat(finalPins.isEnd()).isFalse();
            finalPins.bowl(integer);
        }
        softAssertions.assertThat(finalPins.isEnd()).isTrue();
        softAssertions.assertAll();
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,10,3,4", "10,10,10,3", "10,0,10,4", "8,2,3,2", "0,0,10", "6,4,10,3"})
    @DisplayName("정해진 횟수보다 더 많이 던지면 INVALID_BOWL을 던진다")
    void tooMuchBowlThrowsException(String rawPins) {
        List<Integer> integers = Arrays.stream(rawPins.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        for (int count = 0; count < integers.size() - 1; count++) {
            finalPins.bowl(integers.get(count));
        }
        int finalRawPin = integers.get(integers.size() - 1);
        CustomException customException = assertThrows(CustomException.class, () -> finalPins.bowl(finalRawPin));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_BOWL);
    }
}
