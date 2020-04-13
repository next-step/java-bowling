package bowling.state.finish;

import bowling.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {

    @DisplayName("스트라이크 상태는 종료 된 상태이다.")
    @Test
    public void isDoneTrueTest() {
        State strike = Strike.of();

        assertThat(strike.isFinished()).isTrue();
    }

    @DisplayName("스트라이크 상태에서는 볼을 던질 수 없다.")
    @Test
    public void bowlTest() {
        assertThatThrownBy(() -> Strike.of().bowl(1))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
