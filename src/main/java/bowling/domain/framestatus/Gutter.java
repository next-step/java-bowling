package bowling.domain.framestatus;

public class Gutter implements FrameStatus {

    private static final String GUTTER_FIRST = "|  %s   ";
    private static final String GUTTER_SECOND = "|  %s|%s ";

    private int score;
    private int preScore;
    private String display;

    public Gutter(int score) {
        this.score = score;
        this.preScore = 0;
        this.display = String.format(GUTTER_FIRST, "-");
    }

    public Gutter(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        this.display = String.format(GUTTER_SECOND, convert(preScore), "-");
    }

    private String convert(int value) {
        if (value == 0) {
            return "-";
        }
        return String.valueOf(value);
    }

    @Override
    public String display() {
        return display;
    }

    @Override
    public int getScore() {
        return preScore + score;
    }
}
