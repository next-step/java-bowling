package bowling.domain;

import java.util.List;

public interface Frame {
    Frame initNextFrame();

    void setKnockDownPins(KnockDownPins knockDownPins);

    List<Pitching> getPitchings();

    boolean isEnd();

    Frame getNextFrame();

    int getIndex();

    boolean isLastFrame();
}
