package bowling.domain;

import bowling.domain.State.*;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class NormalFrameTest extends FrameTest {

    @Test
    @DisplayName("first로 생성 테스트")
    void first() {
        NormalFrame first = NormalFrame.first();
        assertThat(first.number()).isEqualTo(FrameNumber.first());
    }

    @Test
    @DisplayName("next로 생성 테스트")
    void next() {
        NormalFrame nextThree = NormalFrame.first().next().next();
        assertThat(nextThree.number()).isEqualTo(new FrameNumber(3));
    }

    @Test
    @DisplayName("프레임 종료된 이후 게임 플레이시 예외 발생 테스트")
    void add_pin_counts_when_done_throw_exception() {
        NormalFrame normalFrame = NormalFrame.first();
        normalFrame.addPinCount(4);
        normalFrame.addPinCount(5);

        assertThat(normalFrame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                normalFrame.addPinCount(5));
    }

    @Test
    @DisplayName("strike시 현재 상태값 테스트")
    void current_state_when_strike() {
        NormalFrame normalFrame = NormalFrame.first().next();
        int strikePinCounts = 10;
        normalFrame.addPinCount(strikePinCounts);

        State state = normalFrame.currentState();
        assertThat(normalFrame.isDone()).isTrue();
        assertThat(state instanceof Strike).isTrue();
        assertThat(state.stateInString()).isEqualTo(STRIKE_SYMBOL);
    }


    @Test
    @DisplayName("spare시 현재 상태값 테스트")
    void current_state_when_spare() {
        NormalFrame normalFrame = NormalFrame.first().next();
        int firstPinCount = 2;
        int secondPinCount = 8;
        normalFrame.addPinCount(firstPinCount);
        normalFrame.addPinCount(secondPinCount);

        State state = normalFrame.currentState();
        assertThat(normalFrame.isDone()).isTrue();
        assertThat(state instanceof Spare).isTrue();
        assertThat(state.stateInString()).isEqualTo(firstPinCount + SPARE_SYMBOL);
    }

    @Test
    @DisplayName("gutter가 없는 miss시 현재 상태값 테스트")
    void current_state_when_miss() {
        NormalFrame normalFrame = NormalFrame.first();
        int firstPinCount = 2;
        int secondPinCount = 6;
        normalFrame.addPinCount(firstPinCount);
        normalFrame.addPinCount(secondPinCount);

        State state = normalFrame.currentState();
        assertThat(normalFrame.isDone()).isTrue();
        assertThat(state instanceof Miss).isTrue();
        assertThat(state.stateInString()).isEqualTo(firstPinCount + SEPARATOR + secondPinCount);
    }

    @Test
    @DisplayName("gutter가 있는 miss시 현재 상태값 테스트")
    void current_state_when_miss_with_gutter() {
        NormalFrame normalFrame = NormalFrame.first();
        int firstPinCount = 0;
        int secondPinCount = 6;
        normalFrame.addPinCount(firstPinCount);
        normalFrame.addPinCount(secondPinCount);

        State state = normalFrame.currentState();
        assertThat(normalFrame.isDone()).isTrue();
        assertThat(state instanceof Miss).isTrue();
        assertThat(state.stateInString()).isEqualTo(GUTTER_SYMBOL + SEPARATOR + secondPinCount);
    }

    @Test
    @DisplayName("ready시 현재 상태값 테스트")
    void current_state_when_ready() {
        NormalFrame normalFrame = NormalFrame.first();

        State state = normalFrame.currentState();
        assertThat(normalFrame.isDone()).isFalse();
        assertThat(state instanceof Ready).isTrue();
        assertThat(state.stateInString()).isEqualTo(EMPTY_SYMBOL);
    }

    @Test
    @DisplayName("hit시 현재 상태값 테스트")
    void current_state_when_hit() {
        NormalFrame normalFrame = NormalFrame.first();
        int firstPinCount = 2;
        normalFrame.addPinCount(firstPinCount);

        State state = normalFrame.currentState();
        assertThat(normalFrame.isDone()).isFalse();
        assertThat(state instanceof Hit).isTrue();
        assertThat(state.stateInString()).isEqualTo(new PinCount(firstPinCount).countInString());
    }


    @Test
    @DisplayName("gutter시 현재 상태값 테스트")
    void current_state_when_gutter() {
        NormalFrame normalFrame = NormalFrame.first();
        int firstPinCount = 0;
        normalFrame.addPinCount(firstPinCount);

        State state = normalFrame.currentState();
        assertThat(normalFrame.isDone()).isFalse();
        assertThat(state instanceof Gutter).isTrue();
        assertThat(state.stateInString()).isEqualTo(GUTTER_SYMBOL);
    }


}
