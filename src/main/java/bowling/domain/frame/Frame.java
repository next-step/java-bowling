package bowling.domain.frame;

public interface Frame {
    void addScore(int score);

    boolean isPlayable();

    int scoreSize();

    String getScore(int frameIndex);
}
