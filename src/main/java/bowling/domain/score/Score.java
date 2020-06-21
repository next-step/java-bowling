package bowling.domain.score;

public interface Score {

    Score nextScore(int point);

    default boolean isStrike() {
        return false;
    }

    default boolean isSpare() {
        return false;
    }

    int getPoint();

    ScoreType getScoreType();
}
