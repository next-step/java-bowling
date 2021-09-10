package bowling.domain;

import java.util.List;

public interface Frame {

    void bowl(int fallenPins);

    boolean isEnd();

    List<PitchResult> results();

    int number();

}
