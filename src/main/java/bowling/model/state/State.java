package bowling.model.state;

import bowling.model.Score;

public abstract class State {
    protected Score score = Score.zero();

    protected State(){}

    protected State(Score score){
        this.score = score;
    }

    public boolean isFinished() {
        return false;
    }

    public abstract State bowling(int fallenPin);

    public int score(){
        return score.getScore();
    }

    public boolean isMaxScore() {
        return score.isMaxScore();
    }
}
