package bowling.model;

import java.util.List;

public interface Round {
    int FIRST_TRY = 1;

    State bowl(int countOfPin);
    Round next(State state, List<Score> scores);
    int calcMaxTryCount();
    Score createScore(int countOfPin);
    List<Score> calculateScore(int countOfPin);
}
