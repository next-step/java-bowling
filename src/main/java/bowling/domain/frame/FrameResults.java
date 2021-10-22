package bowling.domain.frame;

import static bowling.domain.frame.NormalFrame.FINAL_ROUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class FrameResults {

    private final List<FrameResult> frameResults;

    private FrameResults(List<FrameResult> frameResults) {
        this.frameResults = frameResults;
    }

    static FrameResults of(List<FrameResult> frameResults) {
        return new FrameResults(frameResults);
    }

    public static FrameResults createFrameResultsByFirstFrame(Frame frame) {
        List<FrameResult> results = new ArrayList<>();
        results.add(frame.createFrameResult());
        addFrameResult(frame, results);
        return new FrameResults(results);
    }

    private static void addFrameResult(Frame frame, List<FrameResult> results) {
        while (frame.round() < FINAL_ROUND && frame.nextFrame().isPresent()) {
            frame = frame.nextFrame().get();
            results.add(frame.createFrameResult());
        }
    }

    public Stream<FrameResult> values() {
        return frameResults.stream();
    }

    public int size() {
        return frameResults.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameResults that = (FrameResults) o;
        return Objects.equals(frameResults, that.frameResults);
    }

    @Override
    public int hashCode() {
        return Objects.hash(frameResults);
    }

}
