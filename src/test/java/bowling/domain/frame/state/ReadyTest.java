package bowling.domain.frame.state;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {
    @Test
    @DisplayName("첫번째 시도에서 모든 핀을 쓰러트린다면 스트라이크 상태를 반환한다.")
    void bowlWhenStrike() {
        assertThat(new Ready().bowl(10) instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("첫번째 시도에서 모든 핀을 쓰러뜨리지 못하면 FirstBowl 상태를 반환한다.")
    void bowlWhenNotStrike() {
        assertThat(new Ready().bowl(5) instanceof FirstBowl).isTrue();
    }

    @Test
    @DisplayName("해당 상태에서는 해당 프레임이 진행중임을 알린다.")
    void isFinish() {
        assertThat(new Ready().isFinish()).isFalse();
    }
}
