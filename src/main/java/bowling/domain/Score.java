package bowling.domain;

public class Score {

    private static final int GAME_STEP = 1;

    private int firstScore;
    private int secondScore;
    private int score;
    private int bonusScore;
    private int sumScore;

    public Score(int firstScore, int secondScore) {
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.score = firstScore + secondScore;
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

    public void addBonusNumber(State state, Score nextScore) {

        // spare
        if (state == State.SPARE) {
            this.bonusScore = nextScore.firstScore;
            this.sumScore += score + bonusScore;
        }
        // strike
        if (state == State.STRIKE) {
            this.bonusScore = nextScore.score;
            //this.score = this.bonusScore;
            this.sumScore += this.score + this.bonusScore;
        }
        // etc
        if (state != State.SPARE && state != State.STRIKE) {
            this.bonusScore = 0;
            this.score = this.bonusScore;
            this.sumScore += this.score;
        }
        nextScore.sumScore = this.sumScore;
    }


    @Override
    public String toString() {
        return Integer.toString(sumScore);
    }

    public void resetSumScore() {
        sumScore = 0;
    }

}
