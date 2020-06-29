package bowling.domain;

import java.util.List;

public interface Frame {
    public static final int TOTAL_PIN_COUNT = 10;
    public static final int MAX_THROW_COUNT = 2;

    List<ResultType> getResult();

    boolean addScore(int score);
}
