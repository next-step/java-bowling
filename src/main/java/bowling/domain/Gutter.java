package bowling.domain;

public class Gutter implements FrameStatus {

    private static final String GUTTER_FIRST = "|  %s   ";
    private static final String GUTTER_SECOND = "|  %s|%s ";

    private int score;
    private int preScore;
    private String display;

    public Gutter(int score) {
        this.score = score;
        this.display = String.format(GUTTER_FIRST, "-");
    }

    public Gutter(int score, int preScore) {
        this.score = score;
        this.preScore = preScore;
        this.display = String.format(GUTTER_SECOND, "-", "-");
    }

    @Override
    public String display() {
        return display;
    }
}
