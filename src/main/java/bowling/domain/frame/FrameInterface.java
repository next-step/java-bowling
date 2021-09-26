package bowling.domain.frame;

import bowling.domain.score.Pin;
import bowling.domain.score.Score;

public interface FrameInterface {

    FrameInterface createNextFrame();

    FrameInterface nextFrame();

    void updateScoreByPin(Pin pin);

    Score score();

    boolean isNextScore();

}
