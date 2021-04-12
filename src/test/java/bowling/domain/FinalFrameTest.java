package bowling.domain;

import bowling.domain.State.FinalState;
import bowling.domain.State.State;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.FrameNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class FinalFrameTest extends FrameTestBase {

    @Test
    void create() {
        int frameNumber = 10;
        FinalFrame finalFrame = FinalFrame.from(frameNumber);

        assertThat(finalFrame.number()).isEqualTo(new FrameNumber(frameNumber));
    }


    @Test
    @DisplayName("프레임 종료된 이후 게임 플레이시 예외 발생 테스트")
    void add_pin_counts_when_done_throw_exception() {
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(3);
        finalFrame.addPinCount(5);

        assertThat(finalFrame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                finalFrame.addPinCount(5));
    }


    @Test
    @DisplayName("1-ready시 현재 상태값 테스트")
    void current_state_when_1_ready() {
        FinalFrame finalFrame = FinalFrame.from(10);

        State state = finalFrame.currentState();
        assertThat(finalFrame.isDone()).isFalse();
        assertThat(state instanceof FinalState).isTrue();
        assertThat(state.stateInString()).isEqualTo(EMPTY_SYMBOL);
    }

    @Test
    @DisplayName("1-gutter시 현재 상태값 테스트")
    void current_state_when_1_gutter() {
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(0);

        State state = finalFrame.currentState();
        assertThat(finalFrame.isDone()).isFalse();
        assertThat(state instanceof FinalState).isTrue();
        assertThat(state.stateInString()).isEqualTo(GUTTER_SYMBOL);
    }


    @Test
    @DisplayName("1-hit시 현재 상태값 테스트")
    void current_state_when_1_hit() {
        int pinCount = 5;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(pinCount);

        State state = finalFrame.currentState();
        assertThat(finalFrame.isDone()).isFalse();
        assertThat(state instanceof FinalState).isTrue();
        assertThat(state.stateInString()).isEqualTo(pinCount + "");
    }

    @Test
    @DisplayName("3-strike시 현재 상태값 테스트")
    void current_state_when_3_strike() {
        int strikePinCount = 10;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(strikePinCount);


        State state = finalFrame.currentState();
        assertThat(finalFrame.isDone()).isTrue();
        assertThat(state instanceof FinalState).isTrue();
        assertThat(state.stateInString()).isEqualTo(STRIKE_SYMBOL + SEPARATOR + STRIKE_SYMBOL + SEPARATOR + STRIKE_SYMBOL);
    }

    @Test
    @DisplayName("2-strike,1-hit 현재 상태값 테스트")
    void current_state_when_2_strike_1_none() {
        int firstStrikePinCount = 10;
        int secondStrikePinCount = 10;
        int lastPinCount = 3;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstStrikePinCount);
        finalFrame.addPinCount(secondStrikePinCount);
        finalFrame.addPinCount(lastPinCount);

        State state = finalFrame.currentState();
        assertThat(finalFrame.isDone()).isTrue();
        assertThat(state instanceof FinalState).isTrue();
        assertThat(state.stateInString()).isEqualTo(STRIKE_SYMBOL + SEPARATOR + STRIKE_SYMBOL + SEPARATOR + lastPinCount);
    }

    @Test
    @DisplayName("1-strike,1-spare 현재 상태값 테스트")
    void current_state_when_1_strike_1_spare() {
        int strikePinCount = 10;
        int firstSparePinCount = 7;
        int secondSparePinCount = 3;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(firstSparePinCount);
        finalFrame.addPinCount(secondSparePinCount);

        State state = finalFrame.currentState();
        assertThat(finalFrame.isDone()).isTrue();
        assertThat(state instanceof FinalState).isTrue();
        assertThat(state.stateInString()).isEqualTo(STRIKE_SYMBOL + SEPARATOR + firstSparePinCount + SPARE_SYMBOL);
    }

    @Test
    @DisplayName("1-strike,1-miss 현재 상태값 테스트")
    void current_state_when_1_strike_1_miss() {
        int strikePinCount = 10;
        int firstMissPinCount = 4;
        int secondMissPinCount = 3;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(firstMissPinCount);
        finalFrame.addPinCount(secondMissPinCount);

        State state = finalFrame.currentState();
        assertThat(finalFrame.isDone()).isTrue();
        assertThat(state instanceof FinalState).isTrue();
        assertThat(state.stateInString()).isEqualTo(STRIKE_SYMBOL + SEPARATOR + firstMissPinCount + SEPARATOR + secondMissPinCount);
    }

    @Test
    @DisplayName("1-spare,1-hit 현재 상태값 테스트")
    void current_state_when_1_spare_1_hit() {
        int firstSparePinCount = 7;
        int secondSparePinCount = 3;
        int lastPinCount = 5;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstSparePinCount);
        finalFrame.addPinCount(secondSparePinCount);
        finalFrame.addPinCount(lastPinCount);

        State state = finalFrame.currentState();
        assertThat(finalFrame.isDone()).isTrue();
        assertThat(state instanceof FinalState).isTrue();
        assertThat(state.stateInString()).isEqualTo(firstSparePinCount + SPARE_SYMBOL + SEPARATOR + lastPinCount);
    }

    @Test
    @DisplayName("1-spare,1-strike 현재 상태값 테스트")
    void current_state_when_1_spare_1_strike() {
        int firstSparePinCount = 7;
        int secondSparePinCount = 3;
        int strikePinCount = 10;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstSparePinCount);
        finalFrame.addPinCount(secondSparePinCount);
        finalFrame.addPinCount(strikePinCount);

        State state = finalFrame.currentState();
        assertThat(finalFrame.isDone()).isTrue();
        assertThat(state instanceof FinalState).isTrue();
        assertThat(state.stateInString()).isEqualTo(firstSparePinCount + SPARE_SYMBOL + SEPARATOR + STRIKE_SYMBOL);
    }

    @Test
    @DisplayName("1-miss 현재 상태값 테스트")
    void current_state_when_1_miss() {
        int firstMissPinCount = 4;
        int secondMissPinCount = 3;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstMissPinCount);
        finalFrame.addPinCount(secondMissPinCount);

        State state = finalFrame.currentState();
        assertThat(finalFrame.isDone()).isTrue();
        assertThat(state instanceof FinalState).isTrue();
        assertThat(state.stateInString()).isEqualTo(firstMissPinCount + SEPARATOR + secondMissPinCount);
    }



    @Test
    @DisplayName("ready상태의 finalframe 이전 strike frame점수")
    void score_when_strike_frame_before_ready_final_frame() {
        int strikePinCount = 10;
        NormalFrame strikeFrame = NormalFrame.first();
        strikeFrame.addPinCount(strikePinCount);
        strikeFrame.last();
        Score frameScore = strikeFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(strikePinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(2);
    }

    @Test
    @DisplayName("strike+hit상태의 finalframe 이전 strike frame점수")
    void score_when_strike_frame_before_strike_and_hit_final_frame() {
        int strikePinCount = 10;
        int firstPinCountOfFinalFrame = 5;
        NormalFrame strikeFrame = NormalFrame.first();
        strikeFrame.addPinCount(strikePinCount);
        FinalFrame last = strikeFrame.last();
        last.addPinCount(strikePinCount);
        last.addPinCount(firstPinCountOfFinalFrame);

        Score frameScore = strikeFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(strikePinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(2);
    }


    @Test
    @DisplayName("turkey finalframe 이전 strike frame점수")
    void score_when_strike_frame_before_turkey_final_frame() {
        int strikePinCount = 10;
        NormalFrame strikeFrame = NormalFrame.first();
        strikeFrame.addPinCount(strikePinCount);
        FinalFrame last = strikeFrame.last();
        last.addPinCount(strikePinCount);
        last.addPinCount(strikePinCount);
        last.addPinCount(strikePinCount);

        Score frameScore = strikeFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(strikePinCount * 3);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("miss finalframe 이전 strike frame점수")
    void score_when_strike_frame_before_miss_final_frame() {
        int strikePinCount = 10;
        int lastFrameFirstPinCount = 3;
        int lastFrameSecondPinCount = 5;
        NormalFrame strikeFrame = NormalFrame.first();
        strikeFrame.addPinCount(strikePinCount);
        FinalFrame last = strikeFrame.last();
        last.addPinCount(lastFrameFirstPinCount);
        last.addPinCount(lastFrameSecondPinCount);

        Score frameScore = strikeFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(strikePinCount + lastFrameFirstPinCount + lastFrameSecondPinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("strike+spare finalframe 이전 strike frame점수")
    void score_when_strike_frame_before_strike_and_spare_final_frame() {
        int strikePinCount = 10;
        int lastFrameFirstPinCount = 10;
        int lastFrameSecondPinCount = 5;
        int lastFrameThirdPinCount = 5;
        NormalFrame strikeFrame = NormalFrame.first();
        strikeFrame.addPinCount(strikePinCount);
        FinalFrame last = strikeFrame.last();
        last.addPinCount(lastFrameFirstPinCount);
        last.addPinCount(lastFrameSecondPinCount);
        last.addPinCount(lastFrameThirdPinCount);

        Score frameScore = strikeFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(strikePinCount + lastFrameFirstPinCount + lastFrameSecondPinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }


    @Test
    @DisplayName("finalframe miss상태의 점수")
    void score_when_closed_miss() {
        int firstMissPinCount = 4;
        int secondMissPinCount = 3;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstMissPinCount);
        finalFrame.addPinCount(secondMissPinCount);

        Score frameScore = finalFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(firstMissPinCount + secondMissPinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }


    @Test
    @DisplayName("finalframe closed되지 않은 2strike+ready상태의 점수")
    void score_when_not_closed_2_strike_1_ready() {
        int firstStrikePinCount = 10;
        int secondStrikePinCount = 10;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstStrikePinCount);
        finalFrame.addPinCount(secondStrikePinCount);

        Score frameScore = finalFrame.score();

        assertThat(frameScore.isUndefined()).isTrue();
    }

    @Test
    @DisplayName("finalframe close된 1strike+1hit상태의 점수")
    void score_when_closed_1_spare_1_hit() {
        int firstSparePinCount = 4;
        int secondSparePinCount = 6;
        int thirdPinCount = 6;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstSparePinCount);
        finalFrame.addPinCount(secondSparePinCount);
        finalFrame.addPinCount(thirdPinCount);

        Score frameScore = finalFrame.score();

        assertThat(frameScore.scoreInInt()).isEqualTo(firstSparePinCount + secondSparePinCount + thirdPinCount);
        assertThat(frameScore.remainingCount()).isEqualTo(0);
    }

}
