package bowling.domain;

public class Spare implements FrameStatus {

    private static final String SPARE = "|  %s|%s ";

    private int preScore;
    private int score;
    private String display;

    public Spare(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        this.display = String.format(SPARE, preScore, "/");
    }

    @Override
    public String display() {
        return display;
    }
}
