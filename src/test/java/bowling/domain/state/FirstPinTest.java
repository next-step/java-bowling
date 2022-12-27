package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class FirstPinTest {

    @Test
    void 종료_여부_판단() {
        assertThat(new FirstPin(new Pin(0)).isFinished()).isFalse();
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 10", "1, 9"})
    void 게임진행_Spare(int firstPinAmount, int secondPinAmount) {
        assertThat(new FirstPin(new Pin(firstPinAmount)).bowl(new Pin(secondPinAmount)))
                .isInstanceOf(Spare.class);
    }

    @ParameterizedTest
    @CsvSource(value = {"0, 9", "0, 0"})
    void 게임진행_Miss(int firstPinAmount, int secondPinAmount) {
        assertThat(new FirstPin(new Pin(firstPinAmount)).bowl(new Pin(secondPinAmount)))
                .isInstanceOf(Miss.class);
    }
}