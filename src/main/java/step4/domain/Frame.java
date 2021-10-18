package step4.domain;

public interface Frame {

    Score calculateScoreFromNextFrame(Score state);

    void throwBowl(int falledPins, Frames frames);

    String getSymbol();

    Score getScore();

    boolean isGameEnd();

    boolean isFinish();
}
