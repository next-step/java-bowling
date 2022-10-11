package bowling.domain.state;

import bowling.domain.Score;

import java.util.LinkedList;

public abstract class Finished implements State{

    LinkedList <Score> scores;

    public Finished(LinkedList<Score> scores) {
        this.scores = scores;
    }

    @Override
    public boolean isFinish() {
        return true;
    }

}
