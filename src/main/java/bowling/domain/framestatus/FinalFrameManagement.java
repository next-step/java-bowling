package bowling.domain.framestatus;

public class FinalFrameManagement {

    private int score;
    private int preScore;
    private FrameStatus frameStatus;

    public FinalFrameManagement(int score) {
        this.score = score;
        this.frameStatus = getStatusByFirst();
    }

    public FinalFrameManagement(int score, int preScore) {
        this.score = score;
        this.preScore = preScore;
        this.frameStatus = getStatusBySecond();
    }

    private FrameStatus getStatusByFirst() {
        if (score == 10) {
            return new StrikeFinalFrame(score);
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
        if (preScore == 10 && score == 10) {
            return new StrikeFinalFrame(preScore, score);
        }

        if (preScore + score == 10) {
            return new SpareFinalFrame(preScore, score);
        }
        return getStatusBySecondNonSpare();
    }

    private FrameStatus getStatusBySecondNonSpare() {
        if (score == 0) {
            return new Gutter(preScore, score);
        }
        return new Miss(preScore, score);
    }

    public FrameStatus getFrameStatus() {
        return frameStatus;
    }
}
