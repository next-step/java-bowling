package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {

    private static final int INDEX_ZERO = 0;
    private static final int INDEX_ONE = 1;

    List<Frame> frames = new ArrayList<>();

    public boolean add(Frame frame) {
        return frames.add(frame);
    }

    public void replaceFinalFrame(Frame frame) {
        frames.set(frames.size() - INDEX_ONE, frame);
    }

    public List<String> showGameResult() {
        return frames.stream()
                .map(Frame::showResult)
                .collect(Collectors.toList());
    }

    public Frame checkLastFrameState() {
        return frames.stream()
                .reduce((a, b) -> b)
                .orElseThrow(() -> new IllegalStateException());
    }
}
