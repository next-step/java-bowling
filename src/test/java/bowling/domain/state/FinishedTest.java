package bowling.domain.state;

import bowling.domain.pins.Pins;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinishedTest {

    @DisplayName("투구가 끝난 프레임은 더이상 볼을 굴릴 수 없다.")
    @Test
    void frame_bowl_exception() {
        State state = Strike.of();

        assertThatThrownBy(() -> state.bowl(Pins.of(2)))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}