package bowling.domain;

public interface Frame {

    Frame roll(int downPins);

    int numberOfDownedPins();

    int currentFrame();

    boolean hasNextRound();

    Status pinStatus();
}
