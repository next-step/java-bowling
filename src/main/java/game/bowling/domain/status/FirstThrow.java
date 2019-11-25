package game.bowling.domain.status;

import game.bowling.domain.Score;

/**
 * Created by yusik on 2019/11/25.
 */
public class FirstThrow implements Status {

    @Override
    public Status bowl(int fallenPin) {
        if (fallenPin == 10) {
            return new Strike();
        }

        return new SecondThrow(fallenPin);
    }

    @Override
    public Score getScore() {
        return Score.empty();
    }
}
