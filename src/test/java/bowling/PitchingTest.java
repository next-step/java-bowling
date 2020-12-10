package bowling;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class PitchingTest {
    @ParameterizedTest
    @CsvSource(value = {
            "0:GUTTER",
            "1:ONE_PIN",
            "2:TWO_PINS",
            "3:THREE_PINS",
            "4:FOUR_PINS",
            "5:FIVE_PINS",
            "6:SIX_PINS",
            "7:SEVEN_PINS",
            "8:EIGHT_PINS",
            "9:NINE_PINS",
            "10:STRIKE"},
            delimiter = ':')
    public void getPitchingTest(int knockDownPins, Pitching pitching) {
        assertThat(Pitching.getPitching(knockDownPins)).isEqualTo(pitching);
    }

    @Test
    @DisplayName("유효한 knockDownPins이 아닌 경우 throw Exception")
    public void getPitchingTest_failureCase() {
        int invalidKnockDownPins =  -1;
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Pitching.getPitching(invalidKnockDownPins))
                .withMessage(Pitching.INVALID_KNOCK_DOWN_PINS_ARGUMENT);
    }
}
