package bowling.domain;

import java.util.List;

public interface State {

    State roll(int count);

    List<Pin> toPins();

    Score getScore();

    default boolean isFinish() {
        return false;
    }

    default Score sumScore(Score before) {
        if (before.canNextSum()) {
            getScore().sum(before);
        }
        return before;
    }
}