package bowling.domain;

public interface Frame {

    boolean isEndFrame();

    boolean isClear();

    int getNumber();

    int hit(int count);
}