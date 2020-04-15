package bowling.domain.frame.state;

import bowling.domain.Pins;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FinalFrameStatesTest {

    @DisplayName("마지막 프레임은 최대 3개의 상태를 가진다")
    @Test
    public void constructor_fail() throws Exception {
        //given
        LinkedList<State> state = new LinkedList<>();
        state.addAll(Arrays.asList(new Ready(), new Ready(), new Ready()));

        //then
        assertThatThrownBy(
                () -> new FinalFrameStates(state)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("투구 결과의 상태를 추가 할 수 있다")
    @Test
    public void addState_success() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();

        //when
        states.addState(new Ready());
    }

    @DisplayName("첫 투구가 strike 이면 완료 상태가 아니다")
    @Test
    public void isFinish_success_strike() throws Exception {
        //given
        LinkedList<State> state = new LinkedList<>();
        state.addAll(Arrays.asList(new Strike()));
        FinalFrameStates states = new FinalFrameStates(state);

        //when
        assertFalse(states.isFinish());
    }

    @DisplayName("두번째 투구가 spare 이면 완료 상태가 아니다")
    @Test
    public void isFinish_success_spare() throws Exception {
        //given
        LinkedList<State> state = new LinkedList<>();
        state.addAll(Arrays.asList(new Spare(new Pins(3), new Pins(0))));
        FinalFrameStates states = new FinalFrameStates(state);

        //when
        assertFalse(states.isFinish());
    }

    @DisplayName("두번째 투구가 miss 이면 완료 상태 이다")
    @Test
    public void isFinish_success_miss() throws Exception {
        //given
        LinkedList<State> state = new LinkedList<>();
        state.addAll(Arrays.asList(new Miss(new Pins(3), new Pins(0))));
        FinalFrameStates states = new FinalFrameStates(state);

        //when
        assertTrue(states.isFinish());
    }

    @DisplayName("두번째 투구가 gutter 이면 완료 상태 이다")
    @Test
    public void isFinish_success_gutter() throws Exception {
        //given
        LinkedList<State> state = new LinkedList<>();
        state.addAll(Arrays.asList(new Gutter()));
        FinalFrameStates states = new FinalFrameStates(state);

        //when
        assertTrue(states.isFinish());
    }
}
