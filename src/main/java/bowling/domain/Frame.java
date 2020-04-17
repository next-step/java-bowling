package bowling.domain;

import java.util.List;

public interface Frame {
    Frame throwBall(int fallenCount) throws IllegalArgumentException;

    boolean isThrowable();

    int getFrameNo();

    List<Point> getPoints();

    String getScoreMark();
}
