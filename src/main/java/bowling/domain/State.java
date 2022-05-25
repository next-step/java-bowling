package bowling.domain;

public interface State {
    State bowl(Pitching pitching);
    String symbol();
    boolean isEnd();
}
