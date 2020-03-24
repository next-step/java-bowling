package bowling.domain.framestatus;

public class Strike implements FrameStatus {

    private static final String STRIKE = "|  X   ";

    private String display;
    private int score;
    private int preScore;

    public Strike(int score) {
        this.display = STRIKE;
        this.score = score;
        this.preScore = 0;
    }

    public Strike(int score, int preScore) {
        this.score = score;
        this.preScore = preScore;

    }

    @Override
    public String display() {
        return display;
    }

    @Override
    public int getScore() {
        return this.score + preScore;
    }
}
