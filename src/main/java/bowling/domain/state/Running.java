package bowling.domain.state;

import bowling.domain.Score;

import java.util.LinkedList;

public abstract class Running implements State{

    LinkedList<Score> scores;


    public Running(Score score) {
        this.scores = new LinkedList<>();
        this.scores.add(score);
    }

    @Override
    public boolean isFinish() {
        return false;
    }

}
