package bowling.domain.engine.frame;

import bowling.domain.RollResult;

public interface Frame {

    void roll(RollResult rollResult);

    Score getScore();

    Score addScoreTo(Score score);

    boolean isEnded();

    String export();

}
