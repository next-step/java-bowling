package bowling.domain;

public interface Frame {

    void setSecondScore(Score secondScore);

    String convert();

    boolean isStrike();

    boolean isSpare();

    void setThirdScore(Score thirdScore);

    boolean isLastFrame();
}
