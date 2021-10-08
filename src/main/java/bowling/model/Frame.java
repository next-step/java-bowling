package bowling.model;

import java.util.List;

import bowling.CannotBowlException;

public interface Frame {
    State bowl(int countOfPin) throws CannotBowlException;
    void calculateScore(int countOfPin);
    boolean isFinish(int frameNo);
    List<Integer> getScore();
    Frame next(int frameNo);
}
