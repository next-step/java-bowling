package bowling.frame.state;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SetTest {

    @Test
    @DisplayName("첫번째 볼링 - 스트라이크")
    void firstBowlIsStrike() {
        State state = new Set().bowl("10");
        assertThat(state.isFinish()).isTrue();
        assertThat(state instanceof Strike);
    }

    @Test
    @DisplayName("첫번째 볼링 - 다음 회차로 이동")
    void firstBowl() {
        State state = new Set().bowl("3");
        assertThat(state.isFinish()).isFalse();
        assertThat(state instanceof Next);
    }

}
