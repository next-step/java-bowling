package bowling.domain;

public interface Frame {
    int getFrameNum();

    int bowl(int felledPins);

    Rolls getRolls();

    int getFalledPins();
}
