package bowling.domain.framestatus;

public class Spare implements FrameStatus {

    private static final String SPARE = "|  %s|%s ";

    private int preScore;
    private int score;
    private String display;

    public Spare(int score) {
        this.score = score;
        this.preScore = 0;
    }

    public Spare(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        this.display = String.format(SPARE, preScore, "/");
    }

    @Override
    public String display() {
        return display;
    }

    @Override
    public int getScore() {
        return this.score + this.preScore;
    }
}
