package bowling.domain.score;

public abstract class Score {

    final Pin first;
    final Pin second;

    Score(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    public abstract Score nextPin(Pin pin);
    public abstract boolean isNext();

}
