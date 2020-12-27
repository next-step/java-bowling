package bowling.domain.frame;

public interface Frame {

    void record(DownedPin currentTry);

    boolean isEnd();

    int getNumThrown();

    String printStatus();
}
