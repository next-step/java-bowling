package bowling.score;

import bowling.score.rollling.Rolling;

import java.util.List;

public interface Score {
    List<Rolling> getRollings();

    int sumScore();
}
