package bowling;

public interface Thrown {

    boolean isStrike();

    boolean hasTurn();

    void bowl(int secondPins);

    int getScore();

    int firstPins();

    int secondPins();
}
