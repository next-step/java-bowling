package bowling.domain.framestatus;

public class SpareFinalFrame implements FrameStatus {
    private static final String SPARE = "|%s|%s";

    private int preScore;
    private int score;
    private String display;

    public SpareFinalFrame(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        this.display = String.format(SPARE, preScore, "/");
    }

    @Override
    public String display() {
        return display;
    }
}
