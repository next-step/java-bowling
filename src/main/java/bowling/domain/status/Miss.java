package bowling.domain.status;

import bowling.domain.Pin;
import bowling.domain.Score;

public class Miss extends Finished {

    public Miss(Pin first, Pin second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Score getScore() {
        return new Score(this.getCountOfFirst() + this.getCountOfSecond(), 0);
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
