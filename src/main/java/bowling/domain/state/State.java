package bowling.domain.state;

public interface State {
    State bowl(BowlingPin bowlingPin);
    boolean isDone();
    boolean isClear();
    String toSymbol();
}
