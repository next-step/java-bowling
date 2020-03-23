package bowling.domain.framestatus;

public class StrikeFinalFrame implements FrameStatus{
    private static final String STRIKE = "|X";
    private static final String DOUBLE_STRIKE = "|X|X";

    private int preScore;
    private int score;
    private String display;

    public StrikeFinalFrame(int preScore) {
        this.preScore = preScore;
        applyDisplay(preScore);
    }

    public StrikeFinalFrame(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        applyDisplay(preScore, score);
    }

    private void applyDisplay(int preScore) {
        if (preScore == 10) {
            this.display = STRIKE;
        }
    }

    private void applyDisplay(int preScore, int score) {
        if (preScore == 10 && score == 10) {
            this.display = DOUBLE_STRIKE;
        }
    }

    @Override
    public String display() {
        return display;
    }
}
