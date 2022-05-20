package bowling.domain.state;

public interface ThrowingState {
    ThrowingState bowl();
    String symbol();
}
