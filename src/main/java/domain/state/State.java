package domain.state;

public interface State {

    String toSymbol();

    boolean isClosed();
}
