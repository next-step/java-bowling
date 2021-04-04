package bowling.domain;

import bowling.dto.FinalFrameResult;
import bowling.dto.FrameResult;
import bowling.dto.FrameScoreResult;
import bowling.dto.NormalFrameResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

public class FinalFrameTest {

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
        finalFrame.addPintCount(3);
        finalFrame.addPintCount(5);

        assertThat(finalFrame.isDone()).isTrue();
        assertThatIllegalStateException().isThrownBy(() ->
                finalFrame.addPintCount(5));
    }

    @Test
    void result_when_3_strike() {
        int strikePinCount = 10;
        FinalFrame finalFrame = FinalFrame.of(10);
        finalFrame.addPintCount(strikePinCount);
        finalFrame.addPintCount(strikePinCount);
        finalFrame.addPintCount(strikePinCount);

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
        finalFrame.addPintCount(firstStrikePinCount);
        finalFrame.addPintCount(secondStrikePinCount);
        finalFrame.addPintCount(lastPinCount);

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
        finalFrame.addPintCount(strikePinCount);
        finalFrame.addPintCount(firstSparePinCount);
        finalFrame.addPintCount(secondSparePinCount);

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
        finalFrame.addPintCount(strikePinCount);
        finalFrame.addPintCount(firstMissPinCount);
        finalFrame.addPintCount(secondMissPinCount);

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
        finalFrame.addPintCount(firstMissPinCount);
        finalFrame.addPintCount(secondMissPinCount);
        finalFrame.addPintCount(lastPinCount);

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
        finalFrame.addPintCount(firstSparePinCount);
        finalFrame.addPintCount(secondSparePinCount);
        finalFrame.addPintCount(strikePinCount);

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
        finalFrame.addPintCount(firstMissPinCount);
        finalFrame.addPintCount(secondMissPinCount);

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
