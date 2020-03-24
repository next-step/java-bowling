package bowling.domain.framestatus;

public class SpareFinalFrame implements FrameStatus {
    private static final String SPARE = "|%s|%s";
    private static final String BONUS = "|%s";

    private int preScore;
    private int score;
    private String display;

    public SpareFinalFrame(int preScore) {
        this.preScore = preScore;
        this.display = String.format(SPARE, preScore, "/");
    }

    public SpareFinalFrame(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        this.display = String.format(BONUS, preScore, "/", score);
    }

    public void bonus(int score) {
        this.display = this.display+String.format(BONUS, score);
    }

    @Override
    public String display() {
        return display;
    }

    @Override
    public int getScore() {
        return this.score;
    }
}
