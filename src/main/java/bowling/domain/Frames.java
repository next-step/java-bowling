package bowling.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Frames {

    public static final int TOTAL_FRAME_COUNT = 10;

    private List<Frame> frames;
    private List<String> results;

    public Frames() {
        frames = new ArrayList<>();
        results = new ArrayList<>();
    }

    public void throwBalls(int pitchingCount) {
        int index = frames.size() - 1;
        if (index >= 0 && !isNext()) {
            frames.get(index).addScore(pitchingCount);
            results.set(index, frames.get(index).frameScoreToSymbolString());
            return;
        }

        addFrame(pitchingCount);
        index = frames.size() - 1;
        results.add(frames.get(index).frameScoreToSymbolString());
    }

    private void addFrame(int pitchingCount) {
        int framesSize = frames.size();
        if (framesSize < TOTAL_FRAME_COUNT - 1) {
            frames.add(new NormalFrame(pitchingCount));
        }

        if (framesSize == TOTAL_FRAME_COUNT - 1) {
            frames.add(new FinalFrame(pitchingCount));
        }
    }

    public int nextFrameNumber() {
        if (isNext()) {
            return results.size() + 1;
        }
        return results.size();
    }

    public boolean isNext() {
        if (frames.size() == 0) {
            return false;
        }
        int index = frames.size() - 1;
        return frames.get(index).isNext();
    }

    public boolean isFinish() {
        return frames.size() == Frames.TOTAL_FRAME_COUNT
                && frames.get(Frames.TOTAL_FRAME_COUNT - 1).isNext();
    }

    public List<String> results() {
        return Collections.unmodifiableList(results);
    }

    public List<Frame> frames() {
        return Collections.unmodifiableList(frames);
    }
}
