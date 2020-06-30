package bowling.domain;

public class Score {

    private static final int GAME_STEP = 1;

    private int firstScore;
    private int secondScore;
    private int score;
    private int bonusScore;
    private int sumScore;
    private int left;

    public Score(int firstScore, int secondScore, int left) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.score = firstScore + secondScore;
        this.left = left;
    }

    public int getScore() {
        return score;
    }

    public int getFirstScore() {
        return firstScore;
    }

    public int getSumScore() {
        return sumScore;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }

    public void addBonusNumber(Score nextScore) {
        // miss or gutter
        if (left == 0) {
            this.sumScore += score;
        }
        // spare
        if(left == 1) {
            this.sumScore += score + nextScore.firstScore;
        }
        // strike
        if(left == 2) {
            this.sumScore += score + nextScore.score;
        }
        nextScore.sumScore = this.sumScore;
    }

    @Override
    public String toString() {
        return Integer.toString(sumScore);
    }

}
