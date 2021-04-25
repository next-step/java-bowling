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
import static org.junit.jupiter.api.Assertions.assertAll;

class BonusPlayingTest {

    private State bonusState;
    private State finishedRegularState;

    @BeforeEach
    void setUp() {
        finishedRegularState = new Strike(Roll.result(10));
        BonusState bonusReady = new BonusReady(finishedRegularState, 2);
        bonusState = bonusReady.transit(Roll.result(10));
    }

    @Test
    @DisplayName("스페어 처리한 상태가 아니면 다음 상태는 FinishedDoubleRoll 이다.")
    void transitToFinishedSingleRoll() {
        assertThat(bonusState.transit(Roll.result(10)))
            .isInstanceOf(FinishedDoubleRoll.class);
    }

    @Test
    @DisplayName("스페어 처리한 상태이면 다음 상태는 BonusSpare 이다.")
    void transitToBonusSpare() {
        State state = new BonusPlaying(finishedRegularState, Roll.result(8));
        assertThat(state.transit(Roll.result(2)))
            .isInstanceOf(BonusSpare.class);
    }

    @Test
    @DisplayName("두 번의 투구에서 쓰러트린 핀의 수가 10 보다 크면 예외 처리한다.")
    void throwExceptionIfNumberOfPinsExceedTen() {
        State bonusPlaying = new BonusPlaying(finishedRegularState, Roll.result(8));
        assertThatThrownBy(() -> bonusPlaying.transit(Roll.result(3)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("완료 상태를 확인할 때는 항상 false 를 반환한다.")
    void returnFalseIfCallIsFinished() {
        assertThat(bonusState.isFinished()).isFalse();
    }

    @Test
    @DisplayName("BonusPlaying 상태에서는 정규 프레임 상태와 첫 번째 투구의 결과 합쳐 내보낸다.")
    void export() {
        State gutter = new BonusReady(finishedRegularState, 2).transit(Roll.result(0));
        State normal = new BonusReady(finishedRegularState, 2).transit(Roll.result(5));

        assertAll(
            () -> assertThat(bonusState.export()).isEqualTo("X|X"),
            () -> assertThat(gutter.export()).isEqualTo("X|-"),
            () -> assertThat(normal.export()).isEqualTo("X|5")
        );
    }

}
