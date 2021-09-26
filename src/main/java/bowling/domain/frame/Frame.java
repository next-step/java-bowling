package bowling.domain.frame;

import java.util.List;

public interface Frame {

    boolean isNowFirstTry();

    boolean isNowSecondTry();

    int getTotalScore();

    int calculateScore();

    int nextIdx();

    List<Integer> getAllScores();

    Frame bowl(int score);

    boolean isLast();

    int addWithFirstScore(int score);

    Frame getNextFrame();

}
