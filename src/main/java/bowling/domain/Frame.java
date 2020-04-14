package bowling.domain;

public interface Frame {

    boolean throwBall(int fallenCount);

    boolean isThrowable();
}
