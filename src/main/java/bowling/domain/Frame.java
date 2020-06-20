package bowling.domain;

public interface Frame {

    void play(int numberOfDownPin);

    boolean hasTurn();

    FrameBowlStates getBowlStates();
}
