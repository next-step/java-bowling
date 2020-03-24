package bowling.domain.framestatus;

public class FinalFrameManagement {

    private int score;
    private FrameStatus frameStatus;

    public FinalFrameManagement(int score) {
        this.score = score;
        this.frameStatus = applyStatus();
    }

    public FinalFrameManagement(FrameStatus frameStatus, int score) {
        this.score = score;
        this.frameStatus = applyStatusByFrameStatus(frameStatus);
    }

    private FrameStatus applyStatusByFrameStatus(FrameStatus frameStatus) {
        if (frameStatus.isBonus()) {
            if (frameStatus instanceof Strike) {
                return new Strike(frameStatus.getPreScore(), frameStatus.getCurrentScore(), score);
            }
            return new Spare(frameStatus.getPreScore(), frameStatus.getCurrentScore(), score);
        }

        if (frameStatus instanceof Strike) {
            return new Strike(frameStatus.getCurrentScore(), score);
        }

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
