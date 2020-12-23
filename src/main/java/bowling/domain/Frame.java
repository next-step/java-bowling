package bowling.domain;

import java.util.List;

public interface Frame {

    void record(DownedPin currentTry);

    boolean isEnd();

    int numThrown();

    List<DownedPin> exportCurrentStatus();
}
