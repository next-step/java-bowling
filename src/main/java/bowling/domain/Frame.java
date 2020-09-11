package bowling.domain;

public interface Frame {
    Frame next();

    boolean isEndFrame();

    boolean isClear();

    int getNumber();

    int hit(int count);
}