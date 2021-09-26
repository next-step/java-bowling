package bowling.domain.score;

import java.util.List;

public interface Score {

    List<Integer> getAll();

    boolean isStrike();

    boolean isSpare();

    int sum();

    boolean isFirstTryNoPoint();

    boolean isSecondTryNoPoint();

    int getFirst();

    int getSecond();

}
