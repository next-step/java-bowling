package domain.bowling;

import domain.Pins;
import domain.state.Waiting;
import domain.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WaitingSetTest {

    private ReadySet readySet;

    @BeforeEach
    void setUp() {
        readySet = new ReadySet();
    }

    @Test
    @DisplayName("준비 단계 -> 첫번째 기회")
    void bowl() {
        Bowling set = readySet.bowl(Pins.ALL);
        assertThat(set).isInstanceOf(FirstSet.class);
    }

    @Test
    @DisplayName("상태 값 Waiting")
    void getFrameState() {
        State state = readySet.getFrameState();
        assertThat(state).isInstanceOf(Waiting.class);
    }
}