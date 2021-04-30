package step4.domain;

import java.util.List;

public interface Frame {
    Frame throwBowl(String pinCount);

    int index();

    Frame next();

    Score score();

    Score add(Score score);

    boolean isFinished();

    List<String> states();
}
