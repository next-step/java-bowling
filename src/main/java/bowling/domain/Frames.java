package bowling.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Frames {
    private final Map<Integer, Frame> frames;
    private final Map<Integer, FrameScore> frameScores;

    public Frames() {
        this.frames = new HashMap<>();
        this.frameScores = new HashMap<>();
    }

    public void pitch(int frameNo, int point) {
        sumPitchScore(point);

        Frame frame = Optional.ofNullable(frames.get(frameNo))
                .orElse(new Frame(frameNo));

        frames.put(frameNo, frame.pitch(point));
        frameScores.put(frameNo, frame.frameScore());
    }

    private void sumPitchScore(int point) {
        frameScores.values().stream()
                .filter(frameScore -> frameScore.isExistsAddCount())
                .forEach(frameScore -> frameScore.sumScore(point));
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
