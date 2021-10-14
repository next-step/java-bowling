package step4.domain;

public interface Frame {

    Score calculateScoreFromNextFrame(Score state);

    void throwBowl(int i);

    String getSymbol();

    Score getScore();

    boolean isGameEnd();

    boolean isFinish();

    Frame createFrame(int i);

    int round();
}
