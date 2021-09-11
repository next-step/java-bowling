package bowling.domain;

public interface Frame {
    void playShot(Shot shot);

    boolean isFinished();

    boolean isScoringFinished();

    void addPoint(Shot shot);

    int getPoint();

    String toResultString();

    int remainPins();

    default void validate(Shot shot) {
        if (isFinished()) {
            throw new IllegalStateException("Already finished this frame");
        }

        if (remainPins() < shot.getDownCount()) {
            throw new IllegalStateException("can not throw down more than remain pins");
        }
    }
}
