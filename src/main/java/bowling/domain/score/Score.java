package bowling.domain.score;

import bowling.domain.frame.Frame;

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

    Score accumulate(int score);

    int calculateWith(Frame prev);

    int getTotal();

}
