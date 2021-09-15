package bowling.domain;

public interface Frame {

    void bowl(int hitPin);

    boolean isFinished();

    int findCurrentIndex();

    void addPoint(Point point);

    Frame next();

    Points currentFramePoints();

}
