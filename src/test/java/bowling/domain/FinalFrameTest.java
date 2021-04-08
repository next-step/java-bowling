package bowling.domain;

import bowling.domain.State.StateType;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.PinCount;
import bowling.dto.FinalFrameResult;
import bowling.dto.FrameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class FinalFrameTest {

    @Test
    void create_from_try_more_than_3_throw_exception() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                FinalFrame.of(new FrameNumber(10),Arrays.asList(new PinCount(10),new PinCount(10),new PinCount(3),new PinCount(3)))
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"10,7,7", "10,8,8","8,8","5,6"}, delimiter = ':')
    @DisplayName("유효하지 투구값으로 생성")
    void create_from_invalid_pin_count_list_throw_exception(String pinCountsInString) {
        String nameSeparator = ",";
        String[] pinCountsInArray = pinCountsInString.split(nameSeparator);
        List<PinCount> list = Arrays.stream(pinCountsInArray)
                .map(pinCountInString -> new PinCount(Integer.parseInt(pinCountInString)))
                .collect(Collectors.toList());
        assertThatIllegalArgumentException().isThrownBy(() ->
                FinalFrame.of(new FrameNumber(10),list)
        );
    }

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

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        assertThat(finalFrame.isDone()).isTrue();
        for (FrameResult frameResult : frameResults) {
            assertThat(frameResult.frameScoreResult()).isEqualTo(StateType.STRIKE);
            assertThat(frameResult.pinCounts()).containsExactly(strikePinCount);
        }
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

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<StateType> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.STRIKE, StateType.STRIKE, StateType.NONE);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(firstStrikePinCount,secondStrikePinCount,lastPinCount);
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

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<StateType> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.STRIKE, StateType.SPARE);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(strikePinCount,firstSparePinCount,secondSparePinCount);
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

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<StateType> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.STRIKE, StateType.MISS);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(strikePinCount,firstMissPinCount,secondMissPinCount);
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

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<StateType> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.SPARE, StateType.NONE);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(lastPinCount,firstMissPinCount,secondMissPinCount);
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

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<StateType> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.SPARE, StateType.STRIKE);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(strikePinCount,firstSparePinCount,secondSparePinCount);
    }

    @Test
    void result_when_1_miss() {
        int firstMissPinCount = 4;
        int secondMissPinCount = 3;
        FinalFrame finalFrame = FinalFrame.from(10);
        finalFrame.addPinCount(firstMissPinCount);
        finalFrame.addPinCount(secondMissPinCount);

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<StateType> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(StateType.MISS);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(firstMissPinCount,secondMissPinCount);
    }




}
