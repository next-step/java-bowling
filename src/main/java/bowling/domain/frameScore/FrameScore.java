package bowling.domain.frameScore;

public interface FrameScore {
    boolean isCalculated();

    int getScore();

    void addBonus(int shot);
}
