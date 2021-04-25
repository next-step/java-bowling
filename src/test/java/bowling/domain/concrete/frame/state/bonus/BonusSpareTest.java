package bowling.domain.concrete.frame.state.bonus;

import bowling.domain.concrete.frame.state.Strike;
import bowling.domain.engine.frame.state.State;
import bowling.domain.engine.frame.state.bonus.BonusState;
import bowling.domain.engine.roll.Roll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BonusSpareTest {

    private State bonusState;
    private State finishedRegularState;

    @BeforeEach
    void setUp() {
        finishedRegularState = new Strike(Roll.result(10));
        BonusState bonusPlaying = new BonusPlaying(finishedRegularState, Roll.result(8));
        bonusState = bonusPlaying.transit(Roll.result(2));
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
    @DisplayName("첫 번째 투구가 스트라이크면 BonusSpare 상태로 이동할 수 없다.")
    void cannotTransitToBonusSpareIfFirstRollIsStrike() {
        assertThatThrownBy(() -> new BonusSpare(finishedRegularState, Roll.result(10), Roll.result(2)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("두 번째 투구까지 모든 핀을 처리하지 못한 경우에는 BonusSpare 상태로 이동할 수 없다.")
    void cannotTransitToBonusSpareIfNotClearAllPins() {
        assertThatThrownBy(() -> new BonusSpare(finishedRegularState, Roll.result(8), Roll.result(1)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("BonusSpare 상태에서는 마지막 투구를 스페어 문자로 내보낸다.")
    void export() {
        assertThat(bonusState.export()).isEqualTo("X|8|/");
    }


}
