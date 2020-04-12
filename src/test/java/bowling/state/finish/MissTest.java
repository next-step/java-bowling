package bowling.state.finish;

import bowling.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MissTest {

    @DisplayName("미스 상태는 종료 된 상태이다.")
    @Test
    public void isDoneTrueTest() {
        State miss = Miss.of(5, 1);

        assertThat(miss.isFinished()).isTrue();
    }

    @DisplayName("미스 상태에서는 볼을 던질 수 없다.")
    @Test
    public void bowlTest() {
        assertThatThrownBy(() -> Miss.of(1, 2).bowl(3))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("스페어처리 된 상태는 미스상태가 될 수 없다.")
    @Test
    public void beSpareTest() {
        assertThatThrownBy(() -> Miss.of(5, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("스페어처리 된 상태는 미스상태가 될 수 없습니다.");
    }

}
