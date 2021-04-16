package bowling.domain.score;

public interface Score {

    int currentScore();

    int sumCurrentScore(int scoreToSum);

    boolean isNecessaryToCalculateMore();

    boolean isFullyCalculated();

    Score calculatedScore(int toAdd);

}
