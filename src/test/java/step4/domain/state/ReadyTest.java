package step4.domain.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    @Test
    @DisplayName("첫 번째 공을 던질 때 스트라이크일 경우, 스트라이크 객체를 반환한다.")
    public void bowl_strike() throws Exception {
        State state = new Ready().bowl(10);
        assertThat(state instanceof Strike).isTrue();
    }

    @Test
    @DisplayName("첫 번째 공을 던질 때 스트라이크가 아닐 경우, 두 번째 공을 던질 수 있는 객체를 반환한다.")
    public void bowl_not_strike() throws Exception {
        State state = new Ready().bowl(9);
        assertThat(state instanceof FirstBowl).isTrue();
    }
}