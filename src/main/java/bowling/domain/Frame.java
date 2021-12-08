package bowling.domain;

public interface Frame {
    Frame frameAfterPitch(int number);
    boolean continuable();
    Score score();
    boolean remainderLeft();
    void addPins(int pins);
    String state();
}
