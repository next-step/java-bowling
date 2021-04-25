package bowling.domain.concrete.frame.state.bonus;

import bowling.domain.concrete.frame.state.Strike;
import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.bonus.BonusFinished;
import bowling.domain.engine.frame.state.bonus.BonusState;
import bowling.domain.engine.roll.Roll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusReadyTest {

    private BonusState bonusState;
    private State finishedRegularState;

    @BeforeEach
    void setUp() {
        finishedRegularState = new Strike(Roll.result(10));
        bonusState = new BonusReady(bonusState, 1);
    }

    @Test
    @DisplayName("추가 투구 횟수가 1회 또는 2회가 아니면 모두 예외 처리한다.")
    void throwExceptionIfLeftCountIsInvalid() {
        assertThatThrownBy(() -> new BonusReady(finishedRegularState, 3)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("추가 투구 횟수가 1회일 때, 다음 상태는 BonusFinished 이다.")
    void transitToFinishedSingleRoll() {
        assertThat(bonusState.transit(Roll.result(10)))
            .isInstanceOf(BonusFinished.class);
    }

    @Test
    @DisplayName("추가 투구 횟수가 2회일 때, 다음 상태는 BonusPlaying 이다.")
    void transitToBonusPlaying() {
        assertThat(new BonusReady(finishedRegularState, 2).transit(Roll.result(10)))
            .isInstanceOf(BonusPlaying.class);
    }

    @Test
    @DisplayName("완료 상태를 확인할 때는 항상 false 를 반환한다.")
    void returnFalseIfCallIsFinished() {
        assertThat(bonusState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("BonusReady 상태에서는 빈 문자열을 내보낸다.")
    void export() {
        assertThat(bonusState.export()).isEmpty();
    }

}
