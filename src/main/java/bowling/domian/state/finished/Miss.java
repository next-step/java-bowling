package bowling.domian.state.finished;

import bowling.domian.frame.Score;
import bowling.domian.state.Pins;

public class Miss extends Finished {
    // TODO: 2020-08-17 변수명 리팩토링 
    private Pins first;
    private Pins second;

    public Miss(Pins first, Pins second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Score getScore() {
        return Score.miss(first.totalPinsCount(second));
    }

    @Override
    public Score calculateAdditional(Score lastScore) {
        lastScore = first.addScore(lastScore);

        if (!lastScore.isCalculateDone()) {
            lastScore = second.addScore(lastScore);
        }

        return lastScore;
    }

    @Override
    public String getDesc() {
        return first.getPinsCount() + "|" + second.getPinsCount();
    }
}
