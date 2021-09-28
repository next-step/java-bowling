package bowling.model;

import bowling.CannotBowlException;

import java.util.List;
import java.util.LinkedList;

public interface Round {
    int FIRST_TRY = 1;

    State bowl(int countOfPin) throws CannotBowlException;
    int calcMaxTryCount();
    LinkedList<Score> nextScore();
    void calculateScore(int countOfPin);
    boolean isFinish();
}
