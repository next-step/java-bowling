package step4.domain;

public interface Frame {

    Score calculateScoreFromNextFrame(Score state);

    void throwBowl(int i);

    Frame createFrame(int i);

    Score getScore();

    int round();

    Frame next();

    String getSymbol();

    boolean isFinish();

    boolean isGameEnd();
}
