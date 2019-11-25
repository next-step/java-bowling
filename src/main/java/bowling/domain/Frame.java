package bowling.domain;

import java.util.List;

public interface Frame {

    void addScore(int score);

    boolean isEndCondition(int score);

    void checkIsSpare();

    boolean hasSize(int size);

    List<Integer> getScores();

    boolean isSpare();

    boolean isEnd();
}
