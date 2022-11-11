package bowling;

public interface Thrown {

    boolean isStrike();

    boolean isFinished();

    void bowl(int secondPins);

    int getScore();

    int firstPins();

    int secondPins();
}
