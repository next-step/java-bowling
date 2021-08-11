package bowling.domain.score;

public interface Score {

    boolean isCompute();

    Score add(Score score);

    int getValue();

}
