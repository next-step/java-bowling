package bowling.domain.framestatus;

public class Spare implements FrameStatus {

    private static final String SPARE = "|  %s|%s ";
    private static final String FINAL_FRAME_SPARE_BONUS = "|%s|/|%s ";

    private int preScore;
    private int score;
    private int bonusScore;
    private String display;
    private boolean bonus;

    public Spare(int preScore, int score) {
        this.preScore = preScore;
        this.score = score;
        validate();
        this.display = String.format(SPARE, preScore, "/");
        this.bonus = true;
    }

    public Spare(int preScore, int score, int bonusScore) {
        this.preScore = preScore;
        this.score = score;
        validate();
        this.bonusScore = bonusScore;
        this.display = String.format(FINAL_FRAME_SPARE_BONUS, preScore, convert(bonusScore));
    }

    private void validate() {
        if (preScore == 10 || preScore + score != 10) {
            throw new IllegalArgumentException("Spare 의 상태가 될 수 없습니다.");
        }
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
