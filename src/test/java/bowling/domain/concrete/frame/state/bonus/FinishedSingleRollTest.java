package bowling.domain.concrete.frame.state.bonus;

import bowling.domain.concrete.frame.state.Strike;
import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.roll.Roll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class FinishedSingleRollTest {

    private State bonusState;
    private State finishedRegularState;

    @BeforeEach
    void setUp() {
        finishedRegularState = new Strike(Roll.result(10));
        bonusState = new FinishedSingleRoll(finishedRegularState, Roll.result(10));
    }

    @Test
    @DisplayName("더 이상 상태를 변경할 수 없다.")
    void cannotTransit() {
        assertThatThrownBy(() -> bonusState.transit(Roll.result(10)))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("완료 상태를 확인할 때는 항상 true 를 반환한다.")
    void returnTrueIfCallIsFinished() {
        assertThat(bonusState.isFinished()).isTrue();
    }

    @Test
    @DisplayName("현재 투구 결과를 내보낸다.")
    void export() {
        assertAll(
            () -> assertThat(bonusState.export()).isEqualTo("X|X"),
            () -> assertThat(new FinishedSingleRoll(finishedRegularState, Roll.result(0)).export()).isEqualTo("X|-"),
            () -> assertThat(new FinishedSingleRoll(finishedRegularState, Roll.result(5)).export()).isEqualTo("X|5")
        );
    }

}
