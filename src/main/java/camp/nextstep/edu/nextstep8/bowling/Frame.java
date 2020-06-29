package camp.nextstep.edu.nextstep8.bowling;

public class Frame {
    private final Score score;

    private int spare;

    public Frame(int score) {
        this.score = new Score(score);
    }

    public Frame updateSpare(int spare) {
        if(score.meetMaxScore(spare)) {
            throw new IllegalArgumentException("잔여 Spare 점수를 초과하였습니다");
        }
        this.spare = spare;
        return this;
    }

    public String getResultSymbol() {
        if(score.meetMaxScore()) {
            return "X";
        }

        if(score.meetMaxScore(spare)) {
            return score.getScore() + "|/";
        }

        if(isCutter()) {
            return "-";
        }
        return score.getScore() + "|" + spare;
    }

    public boolean isStrikeOrSpare() {
        return score.meetMaxScore() || score.meetMaxScore(spare);
    }

    public boolean hasSpareChance() {
        return !(score.meetMaxScore());
    }

    private boolean isCutter() {
        return 0 == (score.getScore() + spare);
    }
}
