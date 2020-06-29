package bowling.domain;

import java.util.List;

public interface Frame {
    List<ResultType> getResult();

    boolean addScore(int score);
}
