package bowling.domain;

public interface Frame {
    Pitches getPitches();
    Frame frameAfterPitch(int number);
    boolean continuable();
    Score score();
    void addPins(int pins);
}
