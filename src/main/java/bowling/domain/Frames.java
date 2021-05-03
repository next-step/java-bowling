package bowling.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Frames {
    private final Map<Integer, Frame> frames;
    private final Map<Integer, FrameScore> frameScores;

    public Frames() {
        this.frames = new HashMap<>();
        this.frameScores = new HashMap<>();
    }

    public void pitch(int frameNo, int point) {
        accumulatePitchScore(point);

        Frame frame = Optional.ofNullable(frames.get(frameNo))
                .orElse(new Frame(frameNo));

        frames.put(frameNo, frame.pitch(point));
        frameScores.put(frameNo, frame.frameScore());
    }

    private List<FrameScore> accumulatePitchScore(int point) {
        return frameScores.values()
                .stream()
                .map(frameScore -> frameScore.sumScore(point))
                .collect(Collectors.toList())
                ;
    }

    public boolean contains(int frameNo) {
        return frames.containsKey(frameNo);
    }

    public Frame get(int frameNo) {
        return frames.get(frameNo);
    }

    public Map<Integer, Frame> frames() {
        return Collections.unmodifiableMap(frames);
    }

    public Map<Integer, FrameScore> frameScores() {
        return Collections.unmodifiableMap(frameScores);
    }
}
