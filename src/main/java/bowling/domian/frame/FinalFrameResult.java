package bowling.domian.frame;

import bowling.domian.state.State;

public class FinalFrameResult {

    public static FinalFrameResult get(State firstFrameResult) {
        return new FinalFrameResult();
    }

    public static FinalFrameResult get(State firstFrameResult, int totalScore) {
        return new FinalFrameResult();
    }

    public static FinalFrameResult get(State firstFrameResult, State secondFrameResult) {
        return new FinalFrameResult();
    }

    public static FinalFrameResult get(State firstFrameResult, State secondFrameResult, int totalScore) {
        return new FinalFrameResult();
    }

    public boolean canCalculateScore() {
        return false;
    }

    public void calculateAdditional(FrameResult lastFrameResult) {

    }

    public boolean isCalculateDone() {
        return false;
    }

    public boolean getTotalScore() {
        return false;
    }

    public void addLastTotalScore(FrameResult lastFrameResult) {

    }
}
