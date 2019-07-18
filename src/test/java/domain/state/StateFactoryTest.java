package domain.state;

import domain.Pins;
import domain.bowling.SecondSet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

class StateFactoryTest {

    @Test
    @DisplayName("스트라이크")
    void firstStrikeState() {
        State state = StateFactory.firstState(Pins.ALL);
        assertThat(state).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("두번째 준비")
    void firstWaitingState() {
        State state = StateFactory.firstState(Pins.of(9));
        assertThat(state).isInstanceOf(Waiting.class);
    }

    @Test
    @DisplayName("스페어 처리")
    void secondSparesState() {
        State state = StateFactory.secondState(Pins.of(9), Pins.of(1));
        assertThat(state).isInstanceOf(Spares.class);
    }
    @Test
    @DisplayName("Open 상태")
    void secondOpenState() {
        State state = StateFactory.secondState(Pins.of(9), Pins.of(0));
        assertThat(state).isInstanceOf(Open.class);
    }

    @Test
    @DisplayName("두번째시도 스트라이크 예외처리")
    void strikeException() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            StateFactory.secondState(Pins.ALL, Pins.of(0));
        });
    }
}