package bowling.domain;

public interface Frame {
    Pitches getPitches();
    Frame frameAfterPitch(int number);
    boolean continuable();
    Score score();
    boolean remainderLeft();
    void addPins(int pins);
}
