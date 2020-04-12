package bowling.state.finish;

import bowling.state.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GutterTest {

    @DisplayName("거터 상태는 종료 된 상태이다.")
    @Test
    public void isDoneTrueTest() {
        State gutter = Gutter.of();

        assertThat(gutter.isFinished()).isTrue();
    }

    @DisplayName("거터 상태에서는 볼을 던질 수 없다.")
    @Test
    public void bowlTest() {
        assertThatThrownBy(() -> {
            Gutter.of().bowl(5);
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
