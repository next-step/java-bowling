package bowling.domain;

import bowling.domain.State.*;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.PinCount;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class NormalFrameTest extends FrameTestBase {

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

    @Test
    @DisplayName("다음 프레임 ready 상태의 strike frame의 점수")
    void score_when_strike_with_next_frame_undone() {
        int strikePinCount = 10;
        NormalFrame strikeFirstFrame = NormalFrame.first();
        strikeFirstFrame.addPinCount(strikePinCount);
        NormalFrame nextFrame =  strikeFirstFrame.next();

        Score frameScore = strikeFirstFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(strikePinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("다음 프레임 hit 상태의 strike frame의 점수")
    void score_when_strike_with_next_frame_one_try() {
        int strikePinCount = 10;
        int nextFrameFirstPinCount = 6;
        NormalFrame strikeFirstFrame = NormalFrame.first();
        strikeFirstFrame.addPinCount(strikePinCount);
        NormalFrame nextFrame =  strikeFirstFrame.next();
        nextFrame.addPinCount(nextFrameFirstPinCount);

        Score frameScore = strikeFirstFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(strikePinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("다음 프레임이 spare로 진행된 strike frame의 점수")
    void score_when_strike_with_next_frame_spare() {
        int strikePinCount = 10;
        int nextFrameFirstPinCount = 6;
        int nextFrameSecondPinCount = 4;
        NormalFrame strikeFirstFrame = NormalFrame.first();
        strikeFirstFrame.addPinCount(strikePinCount);
        NormalFrame nextFrame =  strikeFirstFrame.next();
        nextFrame.addPinCount(nextFrameFirstPinCount);
        nextFrame.addPinCount(nextFrameSecondPinCount);

        Score frameScore = strikeFirstFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(strikePinCount + nextFrameFirstPinCount + nextFrameSecondPinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("다음 두번 프레임이 스트라이크로 진행된 strike frame의 점수")
    void score_when_turkey() {
        int strikePinCount = 10;
        NormalFrame strikeFirstFrame = NormalFrame.first();
        NormalFrame nextFrameFirst =  strikeFirstFrame.next();
        NormalFrame nextFrameSecond =  nextFrameFirst.next();
        strikeFirstFrame.addPinCount(strikePinCount);
        nextFrameFirst.addPinCount(strikePinCount);
        nextFrameSecond.addPinCount(strikePinCount);

        Score frameScore = strikeFirstFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(strikePinCount * 3);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("다음 프레임이 ready 상태인 spare frame의 점수")
    void score_when_spare_with_undone_frame() {
        int firstPinCount = 5;
        int secondPinCount = 5;
        NormalFrame spareFrame = NormalFrame.first();
        NormalFrame next = spareFrame.next();
        spareFrame.addPinCount(firstPinCount);
        spareFrame.addPinCount(secondPinCount);

        Score frameScore = spareFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(firstPinCount + secondPinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("다음 프레임 miss 상태의 spare frame의 점수")
    void score_when_spare_with_miss_frame() {
        int firstPinCount = 5;
        int secondPinCount = 5;
        int nextFrameFirstPinCount = 3;
        int nextFrameSecondPinCount = 5;
        NormalFrame spareFrame =  NormalFrame.first();
        spareFrame.addPinCount(firstPinCount);
        spareFrame.addPinCount(secondPinCount);
        NormalFrame nextFrameFirst =  spareFrame.next();
        nextFrameFirst.addPinCount(nextFrameFirstPinCount);
        nextFrameFirst.addPinCount(nextFrameSecondPinCount);

        Score frameScore = spareFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(firstPinCount + secondPinCount + nextFrameFirstPinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("다음 프렘임이 진행되지 않은 miss frame점수")
    void score_when_miss_with_undone() {
        int firstPinCount = 5;
        int secondPinCount = 3;
        NormalFrame missFrame =  NormalFrame.first();
        missFrame.addPinCount(firstPinCount);
        missFrame.addPinCount(secondPinCount);

        Score frameScore = missFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(firstPinCount + secondPinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("다음 프레임이 closed된 miss frame점수")
    void score_when_miss_with_next_frame_is_closed() {
        int firstPinCount = 5;
        int secondPinCount = 3;
        int nextFrameFirstPinCount = 3;
        int nextFrameSecondPinCount = 5;
        NormalFrame missFrame =  NormalFrame.first();
        missFrame.addPinCount(firstPinCount);
        missFrame.addPinCount(secondPinCount);
        NormalFrame nextFrameFirst =  missFrame.next();
        nextFrameFirst.addPinCount(nextFrameFirstPinCount);
        nextFrameFirst.addPinCount(nextFrameSecondPinCount);

        Score frameScore = missFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(firstPinCount + secondPinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }


    @Test
    @DisplayName("다음 두번의 프레임이 closed된 miss frame점수")
    void score_when_miss_with_next_frame_two_frame_is_closed() {
        int firstPinCount = 5;
        int secondPinCount = 3;
        int nextFrameFirstPinCount = 3;
        int nextFrameSecondPinCount = 5;
        NormalFrame missFrame =  NormalFrame.first();
        missFrame.addPinCount(firstPinCount);
        missFrame.addPinCount(secondPinCount);
        NormalFrame nextFrameFirst =  missFrame.next();
        nextFrameFirst.addPinCount(nextFrameFirstPinCount);
        nextFrameFirst.addPinCount(nextFrameSecondPinCount);
        NormalFrame nextFrameSecond =  nextFrameFirst.next();
        nextFrameSecond.addPinCount(nextFrameFirstPinCount);
        nextFrameSecond.addPinCount(nextFrameSecondPinCount);

        Score frameScore = missFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(firstPinCount + secondPinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("hit frame점수")
    void score_when_hit_state_frame() {
        int firstPinCount = 5;
        NormalFrame hitFrame =  NormalFrame.first();
        hitFrame.addPinCount(firstPinCount);

        Score frameScore = hitFrame.score();

        assertThat(frameScore.isUndefined()).isTrue();
    }

    @Test
    @DisplayName("ready frame점수")
    void score_when_ready_state_frame() {
        NormalFrame readyFrame =  NormalFrame.first();

        Score frameScore = readyFrame.score();

        assertThat(frameScore.isUndefined()).isTrue();
    }

    @Test
    @DisplayName("gutter frame점수")
    void score_when_gutter_state_frame() {
        NormalFrame gutterFrame =  NormalFrame.first();

        Score frameScore = gutterFrame.score();

        assertThat(frameScore.isUndefined()).isTrue();
    }

}
