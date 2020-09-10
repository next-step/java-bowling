package bowling.domain.frame;

public interface Frame {
    void roll(int pin);

    int getPins();

    boolean canRoll();

    int getIndex();
}
