package bowling.state.domain;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.pin.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EndFrameStatesTest {

    @Test
    @DisplayName("10 프레임 상태에서 MISS 를 기록하면 프레임이 종료 된다.")
    void miss() {
        EndFrameStates states = new EndFrameStates();
        states.roll(Pin.of(8));
        states.roll(Pin.of(1));
        assertThat(states.isEnd()).isTrue();
    }

    @Test
    @DisplayName("10 프레임 상태에서 SPARE 를 기록하면 보너스 (1투구) 가 제공 된다.")
    void spare() {
        EndFrameStates states = new EndFrameStates();
        states.roll(Pin.of(8));
        states.roll(Pin.of(2));
        assertThat(states.isEnd()).isFalse();
    }


    @Test
    @DisplayName("10 프레임 상태에서 STRIKE 를 기록하면 보너스 (2투구) 가 제공 된다.")
    void strike() {
        EndFrameStates states = new EndFrameStates();
        states.roll(Pin.of(10));
        assertThat(states.isEnd()).isFalse();
        states.roll(Pin.of(2));
        assertThat(states.isEnd()).isFalse();
    }

}