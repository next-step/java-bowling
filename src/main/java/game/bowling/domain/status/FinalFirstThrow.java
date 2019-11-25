package game.bowling.domain.status;

import game.bowling.domain.FinalScore;
import game.bowling.domain.Score;

/**
 * Created by yusik on 2019/11/26.
 */
public class FinalFirstThrow implements Status {

    @Override
    public Status bowl(int fallenPin) {
        return new FinalSecondThrow(fallenPin);
    }

    @Override
    public Score getScore() {
        return FinalScore.empty();
    }
}
