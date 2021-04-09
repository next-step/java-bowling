package bowling.domain;

import bowling.domain.State.StateType;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.PinCount;
import bowling.dto.FinalFrameResult;
import bowling.dto.PinCountsResult;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class FinalFrameTest {

    @Test
    void create() {
        int frameNumber = 10;
        FinalFrame finalFrame = FinalFrame.from(frameNumber);

        FinalFrameResult result = finalFrame.result();
        assertThat(result.frameNumber()).isEqualTo(frameNumber);
    }


    @Test
    void add_pin_counts_when_done_throw_exception() {
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(3);
        finalFrame.addPinCount(5);

        assertThat(finalFrame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                finalFrame.addPinCount(5));
    }

    @Test
    void result_when_3_strike() {
        int strikePinCount = 10;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(strikePinCount);


        FinalFrameResult result = finalFrame.result();
        List<StateType> actualFrameScoreResults = result.pinCountsResult().stream()
                .map(PinCountsResult::stateType)
                .collect(Collectors.toList());
        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.STRIKE, StateType.STRIKE, StateType.STRIKE);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(strikePinCount,strikePinCount,strikePinCount);
    }

    @Test
    void result_when_2_strike_1_none() {
        int firstStrikePinCount = 10;
        int secondStrikePinCount = 10;
        int lastPinCount = 3;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstStrikePinCount);
        finalFrame.addPinCount(secondStrikePinCount);
        finalFrame.addPinCount(lastPinCount);

        FinalFrameResult result = finalFrame.result();
        List<StateType> actualFrameScoreResults = result.pinCountsResult().stream()
                .map(PinCountsResult::stateType)
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.STRIKE, StateType.STRIKE, StateType.NONE);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstStrikePinCount,secondStrikePinCount,lastPinCount);
    }

    @Test
    void result_when_1_strike_1_spare() {
        int strikePinCount = 10;
        int firstSparePinCount = 7;
        int secondSparePinCount = 3;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(firstSparePinCount);
        finalFrame.addPinCount(secondSparePinCount);

        FinalFrameResult result = finalFrame.result();
        List<StateType> actualFrameScoreResults = result.pinCountsResult().stream()
                .map(PinCountsResult::stateType)
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.STRIKE, StateType.SPARE);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(strikePinCount,firstSparePinCount,secondSparePinCount);
    }

    @Test
    void result_when_1_strike_1_miss() {
        int strikePinCount = 10;
        int firstMissPinCount = 4;
        int secondMissPinCount = 3;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(firstMissPinCount);
        finalFrame.addPinCount(secondMissPinCount);

        FinalFrameResult result = finalFrame.result();
        List<StateType> actualFrameScoreResults = result.pinCountsResult().stream()
                .map(PinCountsResult::stateType)
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.STRIKE, StateType.MISS);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(strikePinCount,firstMissPinCount,secondMissPinCount);
    }

    @Test
    void result_when_1_spare_1_none() {
        int firstMissPinCount = 7;
        int secondMissPinCount = 3;
        int lastPinCount = 5;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstMissPinCount);
        finalFrame.addPinCount(secondMissPinCount);
        finalFrame.addPinCount(lastPinCount);

        FinalFrameResult result = finalFrame.result();
        List<StateType> actualFrameScoreResults = result.pinCountsResult().stream()
                .map(PinCountsResult::stateType)
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.SPARE, StateType.NONE);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(lastPinCount,firstMissPinCount,secondMissPinCount);
    }

    @Test
    void result_when_1_spare_1_strike() {
        int firstSparePinCount = 7;
        int secondSparePinCount = 3;
        int strikePinCount = 10;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstSparePinCount);
        finalFrame.addPinCount(secondSparePinCount);
        finalFrame.addPinCount(strikePinCount);

        FinalFrameResult result = finalFrame.result();
        List<StateType> actualFrameScoreResults = result.pinCountsResult().stream()
                .map(PinCountsResult::stateType)
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.SPARE, StateType.STRIKE);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(strikePinCount,firstSparePinCount,secondSparePinCount);
    }

    @Test
    void result_when_1_miss() {
        int firstMissPinCount = 4;
        int secondMissPinCount = 3;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstMissPinCount);
        finalFrame.addPinCount(secondMissPinCount);

        FinalFrameResult result = finalFrame.result();
        List<StateType> actualFrameScoreResults = result.pinCountsResult().stream()
                .map(PinCountsResult::stateType)
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.MISS);
        assertThat(result.pinCounts()).containsExactlyInAnyOrder(firstMissPinCount,secondMissPinCount);
    }




}
