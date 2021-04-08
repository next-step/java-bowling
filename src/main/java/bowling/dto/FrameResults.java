package bowling.dto;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;

public class FrameResults {

    private List<FrameResult> frameResults = new ArrayList<>();

    public FrameResults(List<Frame> frames) {
        frameResults.addAll(createNormalFrameResults(frames));
        frameResults.addAll(frames.get(frames.size() - 1));
    }

    private List<FrameResult> createNormalFrameResults(List<Frame> frames) {
        List<FrameResult> normalFrameResults = new ArrayList<>();
        for (int i = 0; i < frames.size() -2; i++) {
            Frame frame = frames.get(i);
            frameResults.add(new FrameResult(frame.number(),frame.pinCounts()));
        }
        return normalFrameResults;
    }

    private List<FrameResult> createFinalFrameResult(Frame frame) {
        List<Integer> pinCounts = frame.pinCounts();
        List<NormalFrame> normalFrames = new ArrayList<>();
        new NormalFrame(frame.number())
    }


}
