package bowling.domain;

public class Miss implements FrameStatus {

    private static final String MISS_FIRST = "|  %s   ";
    private static final String MISS_SECOND = "|  %s|%s ";

    private int score;
    private int preScore;
    private String display;

    public Miss(int score) {
        this.score = score;
        this.display = String.format(MISS_FIRST, score);
    }

    public Miss(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        this.display = String.format(MISS_SECOND, preScore, score);
    }

    @Override
    public String display() {
        return display;
    }
}
