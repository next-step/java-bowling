package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.frame.NormalFrames;
import bowling.domain.status.Ready;

import java.util.List;

public class GameRecord {

    private String user;
    private boolean isEnd;
    private NormalFrames normalFrames;
    private FinalFrame finalFrame = new FinalFrame();

    public GameRecord(String user) {
        this.user = user;
        this.normalFrames = new NormalFrames();
    }

    public int getCurrentFrameIndex() {
        return normalFrames.getCurrentFrameIndex();
    }

    public void recordFrame(int score) {
        if (normalFrames.isNormalEnd()) {
            addFinalFrameScore(score);
            return;
        }

        normalFrames.createFrame(score);
    }

    private void addFinalFrameScore(int score) {
        this.finalFrame.bowl(score);
        if (finalFrame.isEnd()) {
            this.isEnd = true;
        }
    }

    public String getUser() {
        return user;
    }

    public List<NormalFrame> getNormalFrames() {
        return normalFrames.getNormalFrames();
    }

    public FinalFrame getFinalFrame() {
        return finalFrame;
    }

    public boolean isEnd() {
        return isEnd;
    }

    public boolean notStartFinalFrame() {
        return finalFrame.getStatus() instanceof Ready;
    }
}
