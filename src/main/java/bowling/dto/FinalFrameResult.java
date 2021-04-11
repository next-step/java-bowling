package bowling.dto;

import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrameResult implements FrameResult {

    private int frameNumber;

    private List<PinCountsResult> pinCountsResults = new ArrayList<>();

    public FinalFrameResult(FrameNumber frameNumber, List<NormalFrame> frames) {
        this.frameNumber = frameNumber.number();
        frames.forEach(normalFrame -> {
            pinCountsResults.add(null);
        });
    }

    public int frameNumber() {
        return frameNumber;
    }

    @Override
    public List<Integer> pinCounts() {
        return pinCountsResults.stream().flatMap(pinCountResult -> pinCountResult.pinCounts().stream())
                .collect(Collectors.toList());
    }

    public List<PinCountsResult> pinCountsResult() {
        return pinCountsResults;
    }

}
