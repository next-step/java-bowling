package bowling.model;

import bowling.CannotBowlException;

import java.util.List;

public interface Round {
    State bowl(int countOfPin) throws CannotBowlException;
    void calculateScore(int countOfPin);
    boolean isFinish();
    List<Integer> getScore();
}
