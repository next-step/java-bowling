package bowling.model.frame.state;

import bowling.model.frame.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NoneTest {

    @DisplayName("싱글톤 확인")
    @Test
    void check_singleton() {
        assertThat(None.getInstance()).isEqualTo(None.getInstance());
    }

    @DisplayName("아무런 상태가 아닐 시 현 프레임을 계속 진행한다")
    @Test
    void isFinished_false() {
        // given
        State none = None.getInstance();

        // when && than
        assertThat(none.isFinished()).isFalse();
    }
}