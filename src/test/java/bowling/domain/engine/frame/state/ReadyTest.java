package bowling.domain.engine.frame.state;

import bowling.domain.engine.roll.Roll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ReadyTest {

    private State readyState;

    @BeforeEach
    void setUp() {
        readyState = new Ready();
    }

    @Test
    @DisplayName("스트라이크를 치면 Strike 상태로 이전된다.")
    void transitToStrike() {
        assertThat(readyState.transit(Roll.result(10))).isInstanceOf(Strike.class);
    }

    @Test
    @DisplayName("스트라이크가 아니면 Playing 상태로 이전된다.")
    void transitToPlaying() {
        assertThat(readyState.transit(Roll.result(7))).isInstanceOf(Playing.class);
    }

    @Test
    @DisplayName("완료 상태를 확인할 때는 항상 false 를 반환한다.")
    void returnFalseIfCallIsFinished() {
        assertThat(readyState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("Ready 상태에서는 빈 문자열을 내보낸다.")
    void export() {
        assertThat(readyState.export()).isEmpty();
    }
    
}
