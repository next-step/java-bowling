package bowling.domain;

public interface Frame {
    Frame bowl(Pitching pitching);
    String symbol();
    int getFrameNumber();
    boolean isEnd();
}
