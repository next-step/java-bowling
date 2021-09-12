package bowling.domain;

public class FinalScore extends Score {

    protected FinalScore(int score) {
        super(score);
    }

    public static FinalScore from(int score) {
        validateScore(score);
        return new FinalScore(score);
    }

    @Override
    public FinalScore next(int score) {
        validateScore(score);
        return new FinalScore(score);
    }

    public boolean isSpareOrStrikeWhenAdd(int score) {
        return getScore() + score >= MAX_SCORE;
    }
}
