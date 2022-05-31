package bowling.domain.state;

public interface ThrowingState {
    ThrowingState bowl(int pins);
    String symbol();
}
