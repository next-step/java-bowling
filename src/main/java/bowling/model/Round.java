package bowling.model;

public interface Round {
    int FIRST_TRY = 1;

    State bowl(int countOfPin);
    Round next(State state);
    int calcMaxTryCount();
}
