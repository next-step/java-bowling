package bowling.domain;

import bowling.dto.FinalFrameResult;
import bowling.dto.FrameResult;
import bowling.dto.FrameScoreResult;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

public class FinalFrameTest {

    @Test
    void create_from_frames_counts_more_than_3_throw_exception() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new FinalFrame(Arrays.asList(new Frame(),new Frame(),new Frame(),new Frame()),new FrameNumber(4)));
    }

    @Test
    void create() {
        int frameNumber = 10;
        FinalFrame finalFrame = FinalFrame.of(frameNumber);

        FinalFrameResult result = finalFrame.result();
        assertThat(result.frameNumber()).isEqualTo(frameNumber);
    }


    @Test
    void add_pin_counts_when_done_throw_exception() {
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.addPinCount(3);
        finalFrame.addPinCount(5);

        assertThat(finalFrame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                finalFrame.addPinCount(5));
    }

    @Test
    void result_when_3_strike() {
        int strikePinCount = 10;
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(strikePinCount);

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        assertThat(finalFrame.isDone()).isTrue();
        for (FrameResult frameResult : frameResults) {
            assertThat(frameResult.frameScoreResult()).isEqualTo(FrameScoreResult.STRIKE);
            assertThat(frameResult.pinCounts()).containsExactly(strikePinCount);
        }
    }

    @Test
    void result_when_2_strike_1_none() {
        int firstStrikePinCount = 10;
        int secondStrikePinCount = 10;
        int lastPinCount = 3;
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.addPinCount(firstStrikePinCount);
        finalFrame.addPinCount(secondStrikePinCount);
        finalFrame.addPinCount(lastPinCount);

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<FrameScoreResult> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(FrameScoreResult.STRIKE,FrameScoreResult.STRIKE,FrameScoreResult.NONE);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(firstStrikePinCount,secondStrikePinCount,lastPinCount);
    }

    @Test
    void result_when_1_strike_1_spare() {
        int strikePinCount = 10;
        int firstSparePinCount = 7;
        int secondSparePinCount = 3;
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(firstSparePinCount);
        finalFrame.addPinCount(secondSparePinCount);

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<FrameScoreResult> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(FrameScoreResult.STRIKE,FrameScoreResult.SPARE);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(strikePinCount,firstSparePinCount,secondSparePinCount);
    }

    @Test
    void result_when_1_strike_1_miss() {
        int strikePinCount = 10;
        int firstMissPinCount = 4;
        int secondMissPinCount = 3;
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.addPinCount(strikePinCount);
        finalFrame.addPinCount(firstMissPinCount);
        finalFrame.addPinCount(secondMissPinCount);

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<FrameScoreResult> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(FrameScoreResult.STRIKE,FrameScoreResult.MISS);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(strikePinCount,firstMissPinCount,secondMissPinCount);
    }

    @Test
    void result_when_1_spare_1_none() {
        int firstMissPinCount = 7;
        int secondMissPinCount = 3;
        int lastPinCount = 5;
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.addPinCount(firstMissPinCount);
        finalFrame.addPinCount(secondMissPinCount);
        finalFrame.addPinCount(lastPinCount);

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<FrameScoreResult> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(FrameScoreResult.SPARE,FrameScoreResult.NONE);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(lastPinCount,firstMissPinCount,secondMissPinCount);
    }

    @Test
    void result_when_1_spare_1_strike() {
        int firstSparePinCount = 7;
        int secondSparePinCount = 3;
        int strikePinCount = 10;
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.addPinCount(firstSparePinCount);
        finalFrame.addPinCount(secondSparePinCount);
        finalFrame.addPinCount(strikePinCount);

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<FrameScoreResult> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(FrameScoreResult.SPARE,FrameScoreResult.STRIKE);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(strikePinCount,firstSparePinCount,secondSparePinCount);
    }

    @Test
    void result_when_1_miss() {
        int firstMissPinCount = 4;
        int secondMissPinCount = 3;
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.addPinCount(firstMissPinCount);
        finalFrame.addPinCount(secondMissPinCount);

        List<FrameResult> frameResults = finalFrame.result().frameResults();
        List<FrameScoreResult> actualFrameScoreResults = frameResults.stream()
                .map(FrameResult::frameScoreResult)
                .collect(Collectors.toList());
        List<Integer> actualPinCounts = frameResults.stream()
                .flatMap(frameResult -> frameResult.pinCounts().stream())
                .collect(Collectors.toList());

        assertThat(finalFrame.isDone()).isTrue();
        assertThat(actualFrameScoreResults).containsExactlyInAnyOrder(FrameScoreResult.MISS);
        assertThat(actualPinCounts).containsExactlyInAnyOrder(firstMissPinCount,secondMissPinCount);
    }




}
