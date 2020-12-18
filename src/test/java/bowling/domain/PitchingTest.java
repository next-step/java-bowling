package bowling.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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
    public void getPitchingTest(Integer knockDownPinsValue, Pitching pitching) {
        assertThat(Pitching.getPitching(KnockDownPins.valueOf(knockDownPinsValue))).isEqualTo(pitching);
    }

    @Test
    public void getSpareTest() {
        Pitching previousPitching = Pitching.ONE_PIN;
        KnockDownPins knockDownPins = KnockDownPins.valueOf(9);

        assertThat(Pitching.getPitching(knockDownPins, previousPitching)).isEqualTo(Pitching.SPARE);
    }

    @Test
    @DisplayName("스트라이크가 아닌 이전 투구와 현재 투구의 쓰러트린 핀의 갯수가 10을 초과하면 throw Exception")
    public void invalidTotalPins() {
        Pitching previousPitching = Pitching.NINE_PINS;
        KnockDownPins knockDownPins = KnockDownPins.valueOf(2);

        Assertions.assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Pitching.getPitching(knockDownPins, previousPitching))
                .withMessage(Pitching.INVALID_REMAIN_PINS_SIZE_INPUT_ERR_MSG);
    }
}
