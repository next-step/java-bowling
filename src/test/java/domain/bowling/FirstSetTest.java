package domain.bowling;

import domain.Pins;
import domain.state.Waiting;
import domain.state.State;
import domain.state.Strike;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FirstSetTest {

    @Test
    @DisplayName("스트라이크 처리")
    void hitStrike() {
        Bowling firstSet = FirstSet.of(Pins.ALL);
        State state = firstSet.getFrameState();
        assertThat(state).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("두번째 기회를 기다린다.")
    void hitNotStrike() {
        Bowling firstSet = FirstSet.of(Pins.of(9));
        State state = firstSet.getFrameState();
        assertThat(state).isInstanceOf(Waiting.class);
    }

    @Test
    @DisplayName("다음 셋으로 진행")
    void bowl() {
        Bowling firstSet = FirstSet.of(Pins.of(9));
        Bowling next = firstSet.bowl(Pins.of(1));
        assertThat(next).isInstanceOf(SecondSet.class);
    }

}