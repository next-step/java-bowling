package bowling.domain;

public interface Frame {

    boolean isLastFrame();

    boolean isClear();

    int getNumber();

    int hit(int count);

    Frame next();
}