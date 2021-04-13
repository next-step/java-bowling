package bowling.domain.score;

public interface Score {

    int currentScore();

    boolean isNecessaryToCalculateMore();

    boolean isFullyCalculated();

    Score calculatedScore(int toAdd);

}
