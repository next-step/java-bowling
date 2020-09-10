package bowling.domain;

import java.util.List;

public class FrameSet {
    private Bowler bowler;
    private Frames frames;

    private FrameSet(String name) {
        bowler = Bowler.from(name);
        frames = Frames.from(Frames.COUNT);
    }

    public static FrameSet from(String name) {
        return new FrameSet(name);
    }

    public boolean isPlaying() {
        return frames.isPlaying();
    }

    public void record(int hitCount) {
        frames.record(hitCount);
    }

    public int getPlayStage() {
        return frames.getPlayStage();
    }

    public List<Frame> getFrames() {
        return frames.getFrames();
    }

    public String getBowler() {
        return bowler.toString();
    }
}
