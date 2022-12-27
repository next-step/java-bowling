package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    void 종료_여부_판단() {
        assertThat(new Ready().isFinished()).isFalse();
    }

    @Test
    void 게임진행_Strike() {
        assertThat(new Ready().bowl(new Pin(10)))
                .isInstanceOf(Strike.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 9})
    void 게임진행_FirstPin(int amount) {
        assertThat(new Ready().bowl(new Pin(amount)))
                .isInstanceOf(FirstPin.class);
    }
}