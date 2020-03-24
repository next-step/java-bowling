package bowling.domain.framestatus;

public class Miss implements FrameStatus {

    private static final String MISS_FIRST = "|  %s   ";
    private static final String MISS_SECOND = "|  %s|%s ";

    private int score;
    private int preScore;
    private String display;

    public Miss(int score) {
        this.score = score;
        validate();
        this.display = String.format(MISS_FIRST, this.score);
    }

    public Miss(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        validate();
        this.display = String.format(MISS_SECOND, checkGutter(this.preScore), this.score);
    }

    private void validate() {
        if (score >= 10) {
            throw new IllegalArgumentException("Miss 의 상태가 될 수 없습니다.");
        }
        if (score + preScore >= 10) {
            throw new IllegalArgumentException("Miss 의 상태가 될 수 없습니다.");
        }
    }

    @Override
    public String display() {
        return display;
    }

    @Override
    public int getPreScore() {
        return this.preScore;
    }

    @Override
    public int getCurrentScore() {
        return this.score;
    }

    @Override
    public boolean isBonus() {
        return false;
    }

    private String checkGutter(int score) {
        if (score == 0) {
            return "-";
        }
        return String.valueOf(score);
    }
}
