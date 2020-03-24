package bowling.domain.framestatus;

public class Strike implements FrameStatus {

    private static final String STRIKE = "|  X   ";
    private static final String FINAL_FRAME_STRIKE = "|X|%s   ";
    private static final String FINAL_FRAME_STRIKE_BONUS = "|X|%s|%s ";

    private String display;
    private int score;
    private int preScore;
    private int bonusScore;
    private boolean bonus;

    public Strike(int score) {
        this.display = STRIKE;
        this.score = score;
        this.preScore = 0;
    }

    public Strike(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        this.display = String.format(FINAL_FRAME_STRIKE, convert(score));
        this.bonus = true;
    }

    public Strike(int preScore, int score, int bonusScore) {
        this.preScore = preScore;
        this.score = score;
        this.bonusScore = bonusScore;
        this.display = String.format(FINAL_FRAME_STRIKE_BONUS, convert(score), convert(bonusScore));
    }

    private String convert(int value) {
        if (value == 10) {
            return "X";
        }
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
    public int getPreScore() {
        return this.preScore;
    }

    @Override
    public int getCurrentScore() {
        return this.score;
    }

    @Override
    public boolean isBonus() {
        return this.bonus;
    }
}
