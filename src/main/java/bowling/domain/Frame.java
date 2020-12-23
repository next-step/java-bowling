package bowling.domain;

public interface Frame {

    void record(DownedPin currentTry);

    boolean isEnd();

    int numThrown();
}
