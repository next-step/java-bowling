package bowling.domain;

import java.util.List;

public interface Frame {

    void pitch(int count);

    boolean isEnd();

    Frame next();

    List<String> getFallenPins();

    Score getScore();

    void calculateScore(int index, int count);

    boolean hasScore();
}
