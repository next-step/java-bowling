package bowling.domain.frame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class FinalFrameTest {

    private FinalFrame finalFrame;

    @BeforeEach
    void setup() {
        finalFrame = new FinalFrame();
    }

    @ParameterizedTest
    @CsvSource(value = {"10:2"}, delimiter = ':')
    @DisplayName("스트라이크일 때는 보너스포인트가 두개이다")
    void canDetermineStrikeBonusStatus(int pin, int bonus) {
        finalFrame.bowl(pin);
        assertThat(finalFrame.bonusAmount()).isEqualTo(bonus);
    }

    @ParameterizedTest
    @CsvSource(value = {"9:1:1", "0:10:1", "3:7:1", "5:5:1"}, delimiter = ':')
    @DisplayName("스페어일 때는 보너스포인트가 한개이다")
    void canDetermineSpareBonusStatus(int rawFirstPin, int rawSecondPin, int bonus) {
        finalFrame.bowl(rawFirstPin);
        finalFrame.bowl(rawSecondPin);
        assertThat(finalFrame.bonusAmount()).isEqualTo(bonus);
    }

    @ParameterizedTest
    @CsvSource(value = {"9:0:0", "0:3:0", "2:7:0", "5:0:0"}, delimiter = ':')
    @DisplayName("스페어일 때는 보너스포인트가 한개이다")
    void canDetermineNoBonusStatus(int rawFirstPin, int rawSecondPin, int bonus) {
        finalFrame.bowl(rawFirstPin);
        finalFrame.bowl(rawSecondPin);
        assertThat(finalFrame.bonusAmount()).isEqualTo(bonus);
    }

}
