package bowling.domain;

public class Score {
    private static final int SCORE_OF_STRIKE = 10;
    private static final int BONUS_COUNT_OF_STRIKE = 2;
    private static final int BONUS_COUNT_OF_SPARE = 1;
    private static final int DEFAULT_COUNT = 0;

    private final int score;
    private final int bonusCount;

    private Score() {
        this(DEFAULT_COUNT, DEFAULT_COUNT);
    }

    private Score(int score, int bonusCount) {
        this.score = score;
        this.bonusCount = bonusCount;
    }

    public static Score init() {
        return new Score();
    }

    public static Score of(int score, int bonusCount) {
        return new Score(score, bonusCount);
    }

    public static Score ofStrike() {
        return new Score(SCORE_OF_STRIKE, BONUS_COUNT_OF_STRIKE);
    }

    public static Score ofSpare(int score) {
        return new Score(score, BONUS_COUNT_OF_SPARE);
    }

    public static Score ofMiss(int score) {
        return new Score(score, DEFAULT_COUNT);
    }

    public Score next(Score score) {
        return new Score(this.score + score.score, this.bonusCount - 1);
    }

    public boolean calculated() {
        return this.bonusCount == DEFAULT_COUNT;
    }
}
