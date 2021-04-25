package bowling.domain.concrete.frame.state;

import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.roll.Roll;
import bowling.domain.engine.roll.RollResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StrikeTest {

    private State strikeState;

    @BeforeEach
    void setUp() {
        RollResult firstRoll = Roll.result(10);
        strikeState = new Strike(firstRoll);
    }

    @Test
    @DisplayName("Strike 상태는 더 이상 다른 상태로 바뀔 수 없다.")
    void cannotTransit() {
        assertThatThrownBy(() -> strikeState.transit(Roll.result(0)))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("완료 상태를 확인할 때는 항상 true 를 반환한다.")
    void returnFalseIfCallIsFinished() {
        assertThat(strikeState.isFinished()).isTrue();
    }

    @Test
    @DisplayName("Strike 상태에서는 X 로 표기하며, 이를 내보낸다.")
    void export() {
        assertThat(strikeState.export()).isEqualTo("X");
    }

    @Test
    @DisplayName("핀을 전부 쓰러트리지 않은 결과로 초기화하면 예외 처리한다.")
    void throwExceptionIfSomePinsRemain() {
        assertThatThrownBy(() -> new Strike(Roll.result(6))).isInstanceOf(IllegalArgumentException.class);
    }
    
}

