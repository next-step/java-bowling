package bowling.domain.pin;

import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
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

    private FinalPins finalPins;

    @BeforeEach
    void setup() {
        finalPins = new FinalPins();
    }

    @ParameterizedTest
    @CsvSource(value = {"10:true", "9:false", "8:false", "7:false", "6:false",
            "5:false", "4:false", "1:false", "3:false", "0:false"}, delimiter = ':')
    @DisplayName("스트라이크를 판단할 수 있다")
    void canDetermineStrike(int rawFirstPin, boolean expected) {
        finalPins.bowl(rawFirstPin);
        assertThat(finalPins.isStrike()).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"10:0:false", "9:1:true", "8:2:true", "7:3:true", "6:4:true",
            "5:5:true", "4:3:false", "8:1:false", "5:3:false", "0:2:false"}, delimiter = ':')
    @DisplayName("스페어처리를 판단할 수 있다")
    void canDetermineSpare(int rawFirstPin, int rawSecondPin, boolean expected) {
        finalPins.bowl(rawFirstPin);
        finalPins.bowl(rawSecondPin);
        assertThat(finalPins.isSpare()).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,10,3,4", "10,10,10,3", "10,0,10,4", "8,2,3,2", "0,0,10"})
    @DisplayName("정해진 횟수보다 더 많이 던지면 INVALID_BOWL을 던진다")
    void tooMuchBowlThrowsException(String rawPins) {
        List<Integer> integers = Arrays.stream(rawPins.split(","))
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        for(int count = 0;count<integers.size()-1;count++){
            finalPins.bowl(integers.get(count));
        }
        int finalRawPin = integers.get(integers.size()-1);
        CustomException customException = assertThrows(CustomException.class, ()-> finalPins.bowl(finalRawPin));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_BOWL);
    }
}
