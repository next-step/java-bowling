package bowling.domain.frameresult;

public interface FrameResult {

    int calculateScore();

    boolean isCalculated();

    void addBonus(int bonus);
}
