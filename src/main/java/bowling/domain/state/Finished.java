package bowling.domain.state;

import bowling.domain.Score;
import bowling.domain.Scores;

import java.util.List;

public abstract class Finished implements State {

    private Scores scores;

    protected Finished(Scores scores) {
        this.scores = scores;
    }

    @Override
    public State bowl(Score score) {
        throw new IllegalStateException("종료 되었습니다.");
    }

    @Override
    public boolean isFinish() {
        return true;
    }

    @Override
    public List<Integer> getRecord() {
        return scores.getRecord();
    }

    @Override
    public int getRemainPins() {
        return scores.getRemainPins();
    }
}
