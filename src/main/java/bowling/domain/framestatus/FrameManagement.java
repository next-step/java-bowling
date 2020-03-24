package bowling.domain.framestatus;

public class FrameManagement {

    private int score;
    private FrameStatus frameStatus;

    public FrameManagement(int score) {
        this.score = score;
        frameStatus = getStatusByFirst();
    }

    public FrameManagement(int score, FrameStatus frameStatus) {
        this.score = score;
        this.frameStatus = getStatus(frameStatus);
    }

    private FrameStatus getStatus(FrameStatus frameStatus) {
        if (frameStatus.getScore() + score == 10) {
            return new Spare(frameStatus.getScore() + score);
        }

        if (score == 0) {
            return new Gutter(frameStatus.getScore(), score);
        }

        return new Miss(frameStatus.getScore(), score);
    }

    private FrameStatus getStatusByFirst() {
        if (score == 10) {
            return new Strike(score);
        }
        return getStatusByFirstNonStrike();
    }

    private FrameStatus getStatusByFirstNonStrike() {
        if (score == 0) {
            return new Gutter(score);
        }
        return new Miss(score);
    }

    public FrameStatus getFrameStatus() {
        return frameStatus;
    }
}
