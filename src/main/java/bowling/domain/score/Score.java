package bowling.domain.score;

public abstract class Score {

    final Pin first;
    final Pin second;

    Score(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    abstract Score createFirstPin(Pin pin);
    abstract Score createSecondPin(Pin pin);

}
