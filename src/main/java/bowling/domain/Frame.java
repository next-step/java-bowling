package bowling.domain;

public interface Frame {

    void throwBall(int fallenCount) throws IllegalArgumentException;

    boolean isThrowable();
}
