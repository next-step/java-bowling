package bowling.domain.framestatus;

public class Miss implements FrameStatus {

    private static final String MISS_FIRST = "|  %s   ";
    private static final String MISS_SECOND = "|  %s|%s ";

    private int score;
    private int preScore;
    private String display;

    public Miss(int score) {
        this.score = score;
        this.display = String.format(MISS_FIRST, this.score);
    }

    public Miss(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        this.display = String.format(MISS_SECOND, checkGutter(this.preScore), this.score);
    }

    @Override
    public String display() {
        return display;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    private String checkGutter(int score) {
        if (score == 0) {
            return "-";
        }
        return String.valueOf(score);
    }
}
