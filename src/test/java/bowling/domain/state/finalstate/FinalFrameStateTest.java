package bowling.domain.state.finalstate;

import static org.assertj.core.api.Assertions.assertThat;

import bowling.domain.FallingPinCount;
import bowling.domain.state.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FinalFrameStateTest {

    private State init;

    @BeforeEach
    void setup() {
        init = FinalFrameInitState.getInstance();
    }

    @DisplayName("볼링공을 굴린 후 init -> firstState 확인")
    @Test
    void initToFirstState() {
        State firstState = init.roll(FallingPinCount.fromFallingCount(10));
        assertThat(firstState instanceof FinalFrameFirstState).isTrue();
        assertThat(firstState.isDone()).isFalse();
    }

    @DisplayName("볼링공을 굴린 후 firstState => secondState 확인 && 보너스가 없는경우 종료로 나타나는지 확인")
    @Test
    void firstStateToSecondState() {
        State firstState = init.roll(FallingPinCount.fromFallingCount(3));
        State secondState = firstState.roll(FallingPinCount.fromFallingCount(4));
        assertThat(secondState instanceof FinalFrameSecondState).isTrue();
        assertThat(secondState.isDone()).isTrue();
    }

    @DisplayName("스트라이크가 포함된 경우 보너스 까지 진행되는지 확인 && 보너스 투구 후에는 종료로 나타나는지 확인")
    @Test
    void ifStrikeThenHavingBonus() {
        State first = init.roll(FallingPinCount.fromFallingCount(10));
        State second = first.roll(FallingPinCount.fromFallingCount(0));
        assertThat(second.isDone()).isFalse();

        State bonus = second.roll(FallingPinCount.fromFallingCount(10));
        assertThat(bonus instanceof FinalFrameBonusState).isTrue();
        assertThat(bonus.isDone()).isTrue();
    }

    @DisplayName("스페어인 경우 보너스 까지 진행되는지 확인 && 보너스 투구 후에는 종료로 나타나는지 확인")
    @Test
    void ifSpareThenHavingBonus() {
        State first = init.roll(FallingPinCount.fromFallingCount(4));
        State second = first.roll(FallingPinCount.fromFallingCount(6));
        assertThat(second.isDone()).isFalse();

        State bonus = second.roll(FallingPinCount.fromFallingCount(10));
        assertThat(bonus instanceof FinalFrameBonusState).isTrue();
        assertThat(bonus.isDone()).isTrue();
    }
}
