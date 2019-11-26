package bowling.domain;

public interface Score {
    Score ofNext(int pin);

    void bowl(int pin);

    boolean isFinished();
}
