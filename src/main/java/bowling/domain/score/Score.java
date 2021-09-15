package bowling.domain.score;

public abstract class Score {

    final Pin first;
    final Pin second;

    Score(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    public abstract Score createFirstPin(Pin pin);
    public abstract Score createSecondPin(Pin pin);

}
