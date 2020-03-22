package bowling.domain.framestatus;

public class FrameManagement {

    private int score;
    private int preScore;
    private FrameStatus frameStatus;

    public FrameManagement(int score) {
        this.score = score;
        frameStatus = getStatusByFirst();
    }

    public FrameManagement(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        frameStatus = getStatusBySecond();
    }

    private FrameStatus getStatusByFirst() {
        if (score == 10) {
            return new Strike();
        }
        return getStatusByFirstNonStrike();
    }

    private FrameStatus getStatusByFirstNonStrike() {
        if (score == 0) {
            return new Gutter(score);
        }
        return new Miss(score);
    }

    private FrameStatus getStatusBySecond() {
        if (preScore + score == 10) {
            return new Spare(preScore, score);
        }
        return getStatusBySecondNonSpare();
    }

    private FrameStatus getStatusBySecondNonSpare() {
        if (score == 0) {
            return new Gutter(preScore, score);
        }
//        return new Miss(preScore + score);
        return new Miss(preScore, score);
    }

    public FrameStatus getFrameStatus() {
        return frameStatus;
    }
}
