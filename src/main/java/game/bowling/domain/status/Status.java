package game.bowling.domain.status;

import game.bowling.domain.Score;

/**
 * Created by yusik on 2019/11/25.
 */
public interface Status {

    Status bowl(int fallenPin);

    Score getScore();

    default boolean isFinal() {
        return false;
    }
}
