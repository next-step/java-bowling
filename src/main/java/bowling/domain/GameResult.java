package bowling.domain;

import java.util.ArrayList;
import java.util.List;

public class GameResult {

    public static final int NO_FRAME_SIZE = 0;
    public static final int MAX_FRAME_SIZE = 9;

    private String user;
    private boolean isEnd;
    private List<NormalFrame> frames = new ArrayList<>();
    private FinalFrame finalFrame = new FinalFrame();

    public GameResult(String user) {
        this.user = user;
    }

    public int getCurrentFrameIndex() {
        if (frames.size() == NO_FRAME_SIZE || getCurrentFrame().isEnd()) {
            return frames.size() + 1;
        }

        return frames.size();
    }

    public void createFrame(int score) {
        if (this.frames.size() == NO_FRAME_SIZE) {
            NormalFrame frame = new NormalFrame();
            frame.addScore(score);
            this.frames.add(frame);
            return;
        }

        if (!getCurrentFrame().isEnd()) {
            getCurrentFrame().addScore(score);
            return;
        }

        if (this.frames.size() == MAX_FRAME_SIZE) {
            addFinalFrameScore(score);
            return;
        }

        NormalFrame frame = new NormalFrame();
        frame.addScore(score);
        this.frames.add(frame);
    }

    private void addFinalFrameScore(int score) {
        this.finalFrame.addScore(score);
        if (finalFrame.isEnd()) {
            this.isEnd = true;
        }
    }

    private NormalFrame getCurrentFrame() {
        return this.frames.get(frames.size() - 1);
    }

    public String getUser() {
        return user;
    }

    public List<NormalFrame> getFrames() {
        return frames;
    }

    public FinalFrame getFinalFrame() {
        return finalFrame;
    }

    public String getFinalScoreFormat() {
        return finalFrame.getScoreFormat();
    }

    public boolean hasNotFinalFrame() {
        return finalFrame.hasNotFinalFrame();
    }

    public boolean isEnd() {
        return isEnd;
    }
}
