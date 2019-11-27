package bowling.domain.frame;

import java.util.List;

public interface Frame {

    void bowl(int score);

    boolean isEndCondition(int score);

    boolean hasSize(int size);

    List<Integer> getScores();

    boolean isEnd();


}
