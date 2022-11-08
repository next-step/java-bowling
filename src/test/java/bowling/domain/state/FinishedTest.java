package bowling.domain.state;

import bowling.domain.pin.FallenPin;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinishedTest {

    @Test
    void isFinished() {
        assertThat(finished().isFinished()).isTrue();
    }

    @Test
    void bowl() {
        assertThatThrownBy(() -> finished().bowl(FallenPin.of(10)))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("끝난 상태에서는 더이상 투구할 수 없습니다.");
    }

    private Finished finished() {
        return new Miss(FallenPin.of(9), FallenPin.of(0));
    }
}
