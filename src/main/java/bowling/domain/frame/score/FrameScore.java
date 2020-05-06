package bowling.domain.frame.score;

public interface FrameScore {
    boolean isCalculated();

    int getScore();

    void addBonus(int shot);
}
