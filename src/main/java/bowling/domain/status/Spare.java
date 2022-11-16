package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Spare extends Finished {

    public Spare(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Score getScore() {
        return new Score(getCountOfFirst() + getCountOfSecond(), 1);
    }

    @Override
    public Score addScore(Score score) {
        int nextScoreCnt = score.getNextScoreCnt();
        int addScore = 0;
        if (nextScoreCnt == 1) {
            addScore = getCountOfFirst();
        }
        if (nextScoreCnt == 2) {
            addScore = getCountOfFirst() + getCountOfSecond();
        }
        return new Score(score.getValue() + addScore, 0);
    }
}
