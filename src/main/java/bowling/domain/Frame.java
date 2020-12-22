package bowling.domain;

public interface Frame {

    void add(Pitch pitch);

    int getPitchSize();

    boolean isFinish();

    boolean isSpare();

    int getFrameScore();

    int getFirstScore();

    int getSecondScore();

    String getFirstSymbol();

    String getSecondSymbol();
}
