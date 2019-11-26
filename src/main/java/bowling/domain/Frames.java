package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class Frames {

    private static final int FIRST_FRAME_INDEX = 0;
    private static final int INDEX = 1;

    private List<Frame> frames;

    private Frames(List<Frame> frames) {
        this.frames = frames;
    }

    private static Frames of(List<Frame> frames) {
        return new Frames(frames);
    }

    public static Frames initiate(int score) {
        List<Frame> frames = new ArrayList<>();
        frames.add(Frame.first(FIRST_FRAME_INDEX, score));
        return Frames.of(frames);
    }

    public Frames next(int score) {
        Frame recentlyFrame = frames.get(frames.size() - INDEX);
        Frame frameWithScore = Frame.first(recentlyFrame.getPosition() + 1, score);
        frames.add(frameWithScore);
        return Frames.of(frames);
    }

    public Frames nextSecond(int score) {
        Frame recentlyFrame = frames.get(frames.size() - INDEX);
        if (recentlyFrame.isRemain()) {
            Frame frame = recentlyFrame.second(score);
            frames.add(frame);
        }
        return Frames.of(frames);
    }

    public int getValue() {
        Frame recentlyFrame = frames.get(frames.size() - INDEX);
        return recentlyFrame.getPoint();
    }

    public boolean isRemain() {
        Frame recentlyFrame = frames.get(frames.size() - INDEX);
        return recentlyFrame.isRemain();
    }

    public int getLastFramePosition() {
        Frame recentlyFrame = frames.get(frames.size() - INDEX);
        if (recentlyFrame.isRemain()) {
            return recentlyFrame.getPosition();
        }
        return recentlyFrame.getPosition() + INDEX;
    }

    public List<Frame> getFrames() {
        return new ArrayList<>(frames);
    }

    public void applyScore() {
        for (Frame frame : frames) {
            if (ScoreBoard.hasKey(frame.getPosition()) && ScoreBoard.getValue(frame.getPosition()) != null) {
                ScoreBoard.applySecond(frame.getPosition(), frame.getPoint());
            } else {
                ScoreBoard.apply(frame.getPosition(), frame.getPoint());
            }
        }
    }

    public int size() {
        return frames.size();
    }
}
