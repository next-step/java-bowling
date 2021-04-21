package bowling.domain;

import bowling.domain.State.*;
import bowling.domain.frame.FinalFrame;
import bowling.domain.score.FinishedScore;
import bowling.domain.score.Score;
import bowling.domain.score.UnDefinedScore;
import bowling.domain.score.UnFinishedScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class StateTest {

    private static final String READY_STATE = "";

    private static final String SEPARATOR = "|";

    private static final String SPARE_SATE = SEPARATOR+"/";

    private static final String STRIKE_STATE = "X";

    private static final String GUTTER_STATE = "-";

    @Test
    void create_ready() {
        State ready = new Ready();

        assertThat(ready.isClosed()).isFalse();
        assertThat(ready.stateInString()).isEqualTo(READY_STATE);
        assertThat(ready.score() instanceof UnDefinedScore).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                ready.calculatedScore(UnDefinedScore.ofEmpty()));
    }

    @Test
    void create_gutter() {
        State gutter = new Gutter();

        assertThat(gutter.isClosed()).isFalse();
        assertThat(gutter.stateInString()).isEqualTo(GUTTER_STATE);
        assertThat(gutter.score() instanceof UnDefinedScore).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                gutter.calculatedScore(UnDefinedScore.ofEmpty()));
    }

    @Test
    void create_hit() {
        PinCount hitPinCount = PinCount.of(7);

        State hit = new Hit(hitPinCount);

        assertThat(hit.isClosed()).isFalse();
        assertThat(hit.stateInString()).isEqualTo(hitPinCount.countInString());
        assertThat(hit.score() instanceof UnDefinedScore).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                hit.calculatedScore(UnDefinedScore.ofEmpty()));
    }

    @Test
    void create_miss() {
        PinCount firstPinCount = PinCount.of(7);
        PinCount secondPinCount = PinCount.of(1);

        State miss = new Miss(firstPinCount, secondPinCount);

        assertThat(miss.isClosed()).isTrue();
        assertThat(miss.stateInString()).isEqualTo(firstPinCount.countInString() + SEPARATOR + secondPinCount.countInString());
        assertThat(miss.score() instanceof FinishedScore).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:10", "2:9", "7:7", "8:5", "5:7"}, delimiter = ':')
    @DisplayName("잘못된 pinCount로 miss생성시 예외 발생 테스트")
    void create_miss_with_invalid_pinCounts(String first, String second) {
        PinCount firstPinCount = PinCount.of(first);
        PinCount secondPinCount = PinCount.of(second);

        assertThatIllegalArgumentException().isThrownBy(() ->
                new Miss(firstPinCount, secondPinCount));
    }

    @Test
    void miss_calculated_score() {
        PinCount firstPinCount = PinCount.of(7);
        PinCount secondPinCount = PinCount.of(1);
        UnFinishedScore strikeUnFinishedScore = UnFinishedScore.ofStrike(10);

        State miss = new Miss(firstPinCount, secondPinCount);
        Score calculatedScore = miss.calculatedScore(strikeUnFinishedScore);

        assertThat(calculatedScore.currentScore()).isEqualTo(PinCount.STRIKE.count() + firstPinCount.sumCount(secondPinCount));
    }


    @Test
    void create_spare() {
        PinCount firstPinCount = PinCount.of(5);
        PinCount secondPinCount = PinCount.of(5);

        State spare = new Spare(firstPinCount, secondPinCount);

        assertThat(spare.isClosed()).isTrue();
        assertThat(spare.stateInString()).isEqualTo(firstPinCount.countInString() + SPARE_SATE);
        assertThat(spare.score() instanceof UnFinishedScore).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"1:10", "2:9", "7:7", "8:5", "5:7"}, delimiter = ':')
    @DisplayName("잘못된 pinCount로 spare생성시 예외 발생 테스트")
    void create_spare_with_invalid_pinCounts(String first, String second) {
        PinCount firstPinCount = PinCount.of(first);
        PinCount secondPinCount = PinCount.of(second);
        assertThatIllegalArgumentException().isThrownBy(() ->
                new Spare(firstPinCount, secondPinCount));
    }

    @Test
    void spare_calculated_score() {
        PinCount firstPinCount = PinCount.of(5);
        PinCount secondPinCount = PinCount.of(5);
        UnFinishedScore strikeUnFinishedScore = UnFinishedScore.ofStrike(10);

        State spare = new Spare(firstPinCount, secondPinCount);
        Score calculatedScore = spare.calculatedScore(strikeUnFinishedScore);

        assertThat(calculatedScore.currentScore()).isEqualTo(PinCount.STRIKE.count() + firstPinCount.sumCount(secondPinCount));
    }

    @Test
    void create_strike() {
        State strike = new Strike();

        assertThat(strike.isClosed()).isTrue();
        assertThat(strike.stateInString()).isEqualTo(STRIKE_STATE);
        assertThat(strike.score() instanceof UnFinishedScore).isTrue();
    }

    @Test
    void strike_calculated_score() {
        int spareScore = 10;
        UnFinishedScore spareUnFinishedScore = UnFinishedScore.ofSpare(spareScore);

        State strike = new Strike();
        Score calculatedScore = strike.calculatedScore(spareUnFinishedScore);

        assertThat(calculatedScore.currentScore()).isEqualTo(PinCount.STRIKE.count() + spareScore);
    }


    @Test
    @DisplayName("final state 최초 생성시 테스트")
    void final_state_with_1_ready() {
        State finalState = new FinalState();
        UnFinishedScore strikeUnFinishedScore = UnFinishedScore.ofStrike(10);

        assertThat(finalState.isClosed()).isFalse();
        assertThat(finalState.score() instanceof UnDefinedScore).isTrue();
        assertThat(finalState.stateInString()).isEqualTo(READY_STATE);
        assertThatIllegalStateException().isThrownBy(() ->
                finalState.calculatedScore(strikeUnFinishedScore));
    }

    @Test
    @DisplayName("final state 1-gutter로 변경시 테스트")
    void final_state_with_1_gutter() {
        State initialFinalState = new FinalState();
        State finalState = initialFinalState.newState(PinCount.GUTTER);
        UnFinishedScore strikeUnFinishedScore = UnFinishedScore.ofStrike(10);

        assertThat(finalState.isClosed()).isFalse();
        assertThat(finalState.score() instanceof UnDefinedScore).isTrue();
        assertThat(finalState.stateInString()).isEqualTo(GUTTER_STATE);
        assertThatIllegalStateException().isThrownBy(() ->
                finalState.calculatedScore(strikeUnFinishedScore));
    }


    @Test
    @DisplayName("final state 1-hit로 변경시 테스트")
    void final_state_with_1_hit() {
        PinCount hitPinCount = PinCount.of(5);
        State initialFinalState = new FinalState();
        State finalState = initialFinalState.newState(hitPinCount);
        UnFinishedScore strikeUnFinishedScore = UnFinishedScore.ofStrike(10);

        assertThat(finalState.isClosed()).isFalse();
        assertThat(finalState.score() instanceof UnDefinedScore).isTrue();
        assertThat(finalState.stateInString()).isEqualTo(hitPinCount.countInString());
        assertThatIllegalStateException().isThrownBy(() ->
                finalState.calculatedScore(strikeUnFinishedScore));
    }


    @Test
    @DisplayName("final state 1-miss로 변경시 테스트")
    void final_state_when_1_miss() {
        PinCount firstMissPinCount = PinCount.of(4);
        PinCount secondMissPinCount = PinCount.of(3);
        State finalState = new FinalState(Arrays.asList(new Miss(firstMissPinCount,secondMissPinCount)),1);
        UnFinishedScore strikeUnFinishedScore = UnFinishedScore.ofStrike(10);

        assertThat(finalState.isClosed()).isTrue();
        assertThat(finalState.score() instanceof FinishedScore).isTrue();
        assertThat(finalState.stateInString()).isEqualTo(firstMissPinCount.countInString() + SEPARATOR + secondMissPinCount.countInString());
        assertThat(finalState.calculatedScore(strikeUnFinishedScore).currentScore()).isEqualTo(PinCount.STRIKE.count() + firstMissPinCount.sumCount(secondMissPinCount));
    }

    @Test
    @DisplayName("final state 3-strike 테스트")
    void final_state_when_3_strike() {
        int strikePinCount = 10;
        State finalState = new FinalState(Arrays.asList(new Strike(),new Strike(),new Strike()),3);
        UnFinishedScore strikeUnFinishedScore = UnFinishedScore.ofStrike(strikePinCount);

        assertThat(finalState.isClosed()).isTrue();
        assertThat(finalState.score() instanceof FinishedScore).isTrue();
        assertThat(finalState.stateInString()).isEqualTo(STRIKE_STATE + SEPARATOR + STRIKE_STATE + SEPARATOR + STRIKE_STATE);
        assertThat(finalState.calculatedScore(strikeUnFinishedScore).currentScore()).isEqualTo(strikePinCount * 3);
    }

    @Test
    @DisplayName("final state 2-strike,1-hit 테스트")
    void final_state_when_2_strike_1_none() {
        PinCount lastPinCount = PinCount.of(4);
        State finalState = new FinalState(Arrays.asList(new Strike(),new Strike(),new Hit(lastPinCount)),3);
        UnFinishedScore spareUnfinishedScore = UnFinishedScore.ofSpare(10);

        assertThat(finalState.isClosed()).isTrue();
        assertThat(finalState.score() instanceof FinishedScore).isTrue();
        assertThat(finalState.stateInString()).isEqualTo(STRIKE_STATE + SEPARATOR + STRIKE_STATE + SEPARATOR + lastPinCount.countInString());
        assertThat(finalState.calculatedScore(spareUnfinishedScore).currentScore()).isEqualTo(PinCount.STRIKE.count() + spareUnfinishedScore.currentScore());
    }

    @Test
    @DisplayName("final state 1-strike,1-spare 테스트")
    void final_state_when_1_strike_1_spare() {
        PinCount firstPinCount = PinCount.of(4);
        PinCount secondPinCount = PinCount.of(6);
        State finalState = new FinalState(Arrays.asList(new Strike(),new Spare(firstPinCount,secondPinCount)),3);
        UnFinishedScore spareUnfinishedScore = UnFinishedScore.ofSpare(10);

        assertThat(finalState.isClosed()).isTrue();
        assertThat(finalState.score() instanceof FinishedScore).isTrue();
        assertThat(finalState.stateInString()).isEqualTo(STRIKE_STATE + SEPARATOR + firstPinCount.countInString() + SPARE_SATE);
        assertThat(finalState.calculatedScore(spareUnfinishedScore).currentScore()).isEqualTo(PinCount.STRIKE.count() + spareUnfinishedScore.currentScore());
    }

    @Test
    @DisplayName("1-strike,1-miss 현재 상태값 테스트")
    void final_state_when_1_strike_1_miss() {
        PinCount firstPinCount = PinCount.of(4);
        PinCount secondPinCount = PinCount.of(4);
        State finalState = new FinalState(Arrays.asList(new Strike(),new Miss(firstPinCount,secondPinCount)),3);
        UnFinishedScore spareUnfinishedScore = UnFinishedScore.ofSpare(10);

        assertThat(finalState.isClosed()).isTrue();
        assertThat(finalState.score() instanceof FinishedScore).isTrue();
        assertThat(finalState.stateInString()).isEqualTo(STRIKE_STATE + SEPARATOR + firstPinCount.countInString() + SEPARATOR + secondPinCount.countInString());
        assertThat(finalState.calculatedScore(spareUnfinishedScore).currentScore()).isEqualTo(PinCount.STRIKE.count() + spareUnfinishedScore.currentScore());
    }

//    @Test
//    @DisplayName("1-spare,1-hit 현재 상태값 테스트")
//    void final_state_when_1_spare_1_hit() {
//        PinCount firstPinCount = PinCount.of(6);
//        PinCount secondPinCount = PinCount.of(4);
//        PinCount thirdPinCount = PinCount.of(4);
//        State finalState = new FinalState(Arrays.asList(new Spare(firstPinCount,secondPinCount),new Hit(thirdPinCount)),3);
//        UnFinishedScore spareUnfinishedScore = UnFinishedScore.ofSpare(10);
//
//        assertThat(finalState.isClosed()).isTrue();
//        assertThat(finalState.score() instanceof FinishedScore).isTrue();
//        assertThat(finalState.stateInString()).isEqualTo(irstSparePinCount + SPARE_SATE + SEPARATOR + lastPinCount);
//        assertThat(finalState.calculatedScore(spareUnfinishedScore).currentScore()).isEqualTo(PinCount.STRIKE.count() + spareUnfinishedScore.currentScore());
//        assertThat(state.stateInString()).isEqualTo(firstSparePinCount + SPARE_SATE + SEPARATOR + lastPinCount);
//    }
//
//    @Test
//    @DisplayName("1-spare,1-strike 현재 상태값 테스트")
//    void current_state_when_1_spare_1_strike() {
//        int firstSparePinCount = 7;
//        int secondSparePinCount = 3;
//        int strikePinCount = 10;
//        FinalFrame finalFrame = FinalFrame.from(10);
//        finalFrame.addPinCount(firstSparePinCount);
//        finalFrame.addPinCount(secondSparePinCount);
//        finalFrame.addPinCount(strikePinCount);
//
//        State state = finalFrame.currentState();
//        assertThat(finalFrame.isDone()).isTrue();
//        assertThat(state instanceof FinalState).isTrue();
//        assertThat(state.stateInString()).isEqualTo(firstSparePinCount + SPARE_SATE + SEPARATOR + STRIKE_STATE);
//    }








}
