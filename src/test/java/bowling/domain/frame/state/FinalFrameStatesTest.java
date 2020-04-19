package bowling.domain.frame.state;

import bowling.domain.score.Score;
import bowling.exception.BowlingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FinalFrameStatesTest {

    @DisplayName("마지막 프레임은 최대 2개의 상태를 가진다")
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
        FinalFrameStates states = FinalFrameStates.of();

        //when
        states = states.bowl(10);

        //then
        assertFalse(states.isFinish());
    }

    @DisplayName("두번째 투구가 spare 이면 완료 상태가 아니다")
    @Test
    public void isFinish_success_spare() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();

        //when
        states = states.bowl(5);
        states = states.bowl(5);

        //then
        assertFalse(states.isFinish());
    }

    @DisplayName("두번째 투구가 miss 이면 완료 상태 이다")
    @Test
    public void isFinish_success_miss() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();

        //when
        states = states.bowl(1);
        states = states.bowl(1);

        //then
        assertTrue(states.isFinish());
    }

    @DisplayName("두번째 투구가 gutter 이면 완료 상태 이다")
    @Test
    public void isFinish_success_gutter() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();

        //when
        states = states.bowl(0);
        states = states.bowl(0);

        //then
        assertTrue(states.isFinish());
    }

    @DisplayName("첫 투구가 strike 이면 한번 더 투구 가능하다")
    @Test
    public void bowl_success_strike() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();
        states = states.bowl(10);

        //when
        states = states.bowl(10);

        //then
        assertTrue(states.isFinish());
    }

    @DisplayName("첫 투구가 strike 일때 두번 더 투구는 불가능 하다")
    @Test
    public void bowl_fail_strikeTwice() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();
        states = states.bowl(10);
        states = states.bowl(10);

        //then
        FinalFrameStates finalStates = states;
        assertTrue(states.isFinish());
        assertThatThrownBy(
                () -> finalStates.bowl(10)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("두번째 투구가 spare 이면 한번 더 투구 가능하다")
    @Test
    public void bowl_success() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();
        states = states.bowl(5);
        states = states.bowl(5);

        //when
        states = states.bowl(10);

        //then
        assertTrue(states.isFinish());
    }

    @DisplayName("두번째 투구가 spare, 세번째 에서 strike를 쳐도 추가로 투구 불가능 하다")
    @Test
    public void bowl_fail_throwTwice() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();
        states = states.bowl(5);
        states = states.bowl(5);
        states = states.bowl(10);


        //then
        FinalFrameStates finalStates = states;
        assertTrue(finalStates.isFinish());
        assertThatThrownBy(
                () -> finalStates.bowl(1)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("두번째 투구가 miss 이면 한번 더 투구 불가능")
    @Test
    public void bowl_fail_miss() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();
        states = states.bowl(1);
        states = states.bowl(2);
        FinalFrameStates finalStates = states;

        //then
        assertTrue(finalStates.isFinish());
        assertThatThrownBy(
                () -> finalStates.bowl(3)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("두번째 투구가 gutter 이면 한번 더 투구 불가능")
    @Test
    public void bowl_fail_gutter() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();
        states = states.bowl(0);
        states = states.bowl(0);
        FinalFrameStates finalStates = states;

        //then
        assertTrue(finalStates.isFinish());
        assertThatThrownBy(
                () -> finalStates.bowl(3)
        ).isInstanceOf(BowlingException.class);
    }

    @DisplayName("finalFrame의 점수 계산")
    @Test
    public void getScore_success() throws Exception {
        //given
        FinalFrameStates states = FinalFrameStates.of();
        states = states.bowl(10);
        states = states.bowl(10);

        //then
        assertThat(states.getScore()).isEqualTo(new Score(20));
    }
}
