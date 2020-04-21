package bowling.domain.score;

public interface Score {
    boolean isCompleted();

    int getScore();

    Score add(Score score);
}
