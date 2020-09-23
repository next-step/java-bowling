package bowling.domain;

import java.util.List;

public interface Frame {

    void pitch(int count);

    boolean isEnd();

    Frame next();

    List<String> getScore();
}
