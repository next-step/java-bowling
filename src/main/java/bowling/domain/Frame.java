package bowling.domain;

public interface Frame {
    Pitches getPitches();
    Frame pitch(int number);
    boolean continuable();
    Score score();
    void addPins(int pins);
}
