package bowling.domain.state;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StateFactoryTest {

    private States states;

    @BeforeEach
    void setup() {
        states = States.initialize();
    }

    @Test
    @DisplayName("initialState 시 Ready 를 반환한다.")
    void initialState() {
        assertThat(StateFactory.initialState()).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("스트라이크 두번 시 다음 State 를 BonusReady 를 반환한다.")
    void nextState_BonusReady() {
        states.bowl(10);
        states.bowl(10);

        assertThat(StateFactory.nextState(states)).isInstanceOf(BonusReady.class);
    }

    @Test
    @DisplayName("스페어 처리 시 다음 State 를 BonusReady 를 반환한다.")
    void nextState_BonusReady_2() {
        states.bowl(3);
        states.bowl(7);

        assertThat(StateFactory.nextState(states)).isInstanceOf(BonusReady.class);
    }

    @Test
    @DisplayName("스트라이크 한번 했을 때 다음 State 를 Ready 를 반환한다.")
    void nextState_Ready() {
        states.bowl(10);

        assertThat(StateFactory.nextState(states)).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("State 가 종료되지 않았을 때 다음 State 를 호출하면 예외를 반환한다.")
    void invalidNextState() {
        states.bowl(4);

        assertThat(states.isFinish()).isFalse();
        assertThatThrownBy(() -> StateFactory.nextState(states)).isInstanceOf(IllegalStateException.class);
    }
}