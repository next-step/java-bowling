package bowling.domain;

public interface Frame {
    Frame throwBall(int fallenCount) throws IllegalArgumentException;

    boolean isThrowable();

    int getFrameNo();

    Points getPoints();

    String getScoreMark();
}
