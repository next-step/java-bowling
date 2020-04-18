package bowling.domain.State;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadyTest {
    @DisplayName("준비상태에서 공을 굴리고난 뒤 스트라이크인지 확인한다")
    @Test
    void strikeBowl() {
        Ready ready = new Ready();
        State state = ready.bowl(10);
        assertTrue(state instanceof Strike);
    }

    @DisplayName("준비상태에서 공을 굴리고난 뒤 첫번째 시도인지 확인한다")
    @Test
    void firstBowl() {
        Ready ready = new Ready();
        State state = ready.bowl(3);
        assertTrue(state instanceof FirstBowl);
    }
}
