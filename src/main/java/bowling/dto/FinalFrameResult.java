package bowling.dto;

import bowling.domain.frame.FrameNumber;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FinalFrameResult implements FrameResult {

    private int frameNumber;

    private List<PinCountResult> pinCountResults = new ArrayList<>();

    public FinalFrameResult(FrameNumber frameNumber, List<NormalFrame> frames) {
        this.frameNumber = frameNumber.number();
        frames.forEach(normalFrame -> {
            pinCountResults.add(normalFrame.result().frameResult());
        });
    }

    public int frameNumber() {
        return frameNumber;
    }

    @Override
    public List<Integer> pinCounts() {
        return pinCountResults.stream().flatMap(pinCountResult -> pinCountResult.pinCounts().stream())
                .collect(Collectors.toList());
    }

    public List<PinCountResult> frameResults() {
        return pinCountResults;
    }

}
