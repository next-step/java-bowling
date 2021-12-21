package bowling.domain.frame;

public interface Frame {
    boolean isBallCount();

    void addPin(int pin);

    boolean strike(int pin);

    int numberOfBall();

    int fallenPin(int ballIndexNumber);
}
