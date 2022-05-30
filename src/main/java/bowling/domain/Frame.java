package bowling.domain;

public interface Frame {
    Frame bowl(Pitching pitching);
    int getFrameNumber();
    boolean isEnd();
    int score();
    int calculationScore(Score before);
}
