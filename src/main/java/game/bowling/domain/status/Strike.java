package game.bowling.domain.status;

import game.bowling.domain.Score;

/**
 * Created by yusik on 2019/11/25.
 */
public class Strike implements LastStatus {

    @Override
    public Status bowl(int fallenPin) {
        return new FirstThrow();
    }

    @Override
    public Score getScore() {
        return new Score(10, 0);
    }
}
