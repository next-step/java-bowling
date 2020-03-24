package bowling.domain.framestatus;

public class FrameManagement {

    private int score;
    private FrameStatus frameStatus;

    public FrameManagement(int score) {
        this.score = score;
        frameStatus = applyStatus();
    }

    public FrameManagement(int score, FrameStatus frameStatus) {
        this.score = score;
        this.frameStatus = applyStatusByFrameStatus(frameStatus);
    }

    private FrameStatus applyStatusByFrameStatus(FrameStatus frameStatus) {
        if (frameStatus.getCurrentScore() + score == 10) {
            return new Spare(frameStatus.getCurrentScore(), score);
        }
        if (score == 0) {
            return new Gutter(frameStatus.getCurrentScore(), score);
        }
        return new Miss(frameStatus.getCurrentScore(), score);
    }

    private FrameStatus applyStatus() {
        if (score == 10) {
            return new Strike(score);
        }
        return applyStatusNonStrike();
    }

    private FrameStatus applyStatusNonStrike() {
        if (score == 0) {
            return new Gutter(score);
        }
        return new Miss(score);
    }

    public FrameStatus getFrameStatus() {
        return frameStatus;
    }
}
