package bowling.domain.state;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StateFactoryTest {

    private Frame finalFrame;

    @BeforeEach
    void setup() throws Exception {
        finalFrame = NormalFrame.initialize();
        for (int i = 1; i < 10; i++) {
            finalFrame.bowling(10);
            finalFrame = finalFrame.next();
        }
    }

    @Test
    @DisplayName("initialState 시 Ready 를 반환한다.")
    void initialState() {
        assertThat(StateFactory.initialState()).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("스트라이크 두번 시 다음 State 를 BonusReady 를 반환한다.")
    void nextState_BonusReady() {
        finalFrame.bowling(10);
        finalFrame.bowling(10);

        assertThat(StateFactory.nextState(finalFrame)).isInstanceOf(BonusReady.class);
    }

    @Test
    @DisplayName("스페어 처리 시 다음 State 를 BonusReady 를 반환한다.")
    void nextState_BonusReady_2() {
        finalFrame.bowling(3);
        finalFrame.bowling(7);

        assertThat(StateFactory.nextState(finalFrame)).isInstanceOf(BonusReady.class);
    }

    @Test
    @DisplayName("스트라이크 한번 했을 때 다음 State 를 Ready 를 반환한다.")
    void nextState_Ready() {
        finalFrame.bowling(10);

        assertThat(StateFactory.nextState(finalFrame)).isInstanceOf(Ready.class);
    }

    @Test
    @DisplayName("State 가 종료되지 않았을 때 다음 State 를 호출하면 예외를 반환한다.")
    void invalidNextState() {
        finalFrame.bowling(4);

        assertThatThrownBy(() -> StateFactory.nextState(finalFrame)).isInstanceOf(IllegalStateException.class);
    }
}