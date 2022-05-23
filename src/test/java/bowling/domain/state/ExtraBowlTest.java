package bowling.domain.state;

import bowling.domain.Score;
import bowling.domain.pin.Pin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ExtraBowlTest {

    @Test
    void additionalScore_For_Spare() {
        ExtraBowl extraBowl = new ExtraBowl(Pin.of(5));
        Score spare = Score.ofSpare();

        Score afterScore = extraBowl.additionalScore(spare);

        assertThat(afterScore.getScore()).isEqualTo(15);
    }

    @ParameterizedTest
    @CsvSource({"10,X", "9,9"})
    void expression(int pinNo, String result) {
        Pin pin = Pin.of(pinNo);
        assertThat(new ExtraBowl(pin).expression())
                .isEqualTo(result);
    }
}
