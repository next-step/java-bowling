package bowling.domain.frame;

import bowling.domain.rolling.Rollings;
import bowling.domain.score.Score;

import java.util.List;

public interface Frame {

    int INIT_NUMBER = 1;
    int LAST_NORMAL_NUMBER = 9;
    int FINAL_NUMBER = 10;

    boolean isEnd();

    Frame next();

    int number();

    Frame roll(int fallenPin);

    Rollings rollings();

    Score score(List<Frame> frames);

    Score addScore(Score before, List<Frame> frames);



}
