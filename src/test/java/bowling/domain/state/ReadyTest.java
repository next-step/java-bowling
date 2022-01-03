package bowling.domain.state;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.Pins;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ReadyTest {

    @Test
    void strike_생성() {
        assertThat(new Ready().bowl(Pins.of(10))).isInstanceOf(Strike.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9})
    void firstBowl_생성(int fallenPins) {
        assertThat(new Ready().bowl(Pins.of(fallenPins))).isInstanceOf(FirstBowl.class);
    }

    @Test
    void 완료여부() {
        assertThat(new Ready().isFinished()).isFalse();
    }

}