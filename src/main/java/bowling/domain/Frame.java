package bowling.domain;

import java.util.List;

public interface Frame {

    void throwBall(int fallenCount) throws IllegalArgumentException;

    boolean isThrowable();

    int getFrameNo();

    List<Point> getPoints();
}
