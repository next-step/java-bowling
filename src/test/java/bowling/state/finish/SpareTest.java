package bowling.state.finish;

import bowling.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpareTest {

    @DisplayName("스페어 상태는 종료 된 상태이다.")
    @Test
    public void isDoneTrueTest() {
        State spare = Spare.of(4);

        assertThat(spare.isFinished()).isTrue();
    }

    @DisplayName("스페어 상태에서는 볼을 던질 수 없다.")
    @Test
    public void bowlTest() {
        assertThatThrownBy(() -> Spare.of(1).bowl(2))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
