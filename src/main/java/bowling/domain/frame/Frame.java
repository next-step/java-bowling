package bowling.domain.frame;

import bowling.domain.score.Score;

public interface Frame {

    Score score();

    boolean isScoreNextStorable();

    void updateScorePin();

    Frame createNextFrame();

    Frame nextFrame();

}
