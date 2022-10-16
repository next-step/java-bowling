package bowling.domain.state;

import bowling.domain.Score;
import bowling.domain.Scores;

import java.util.List;

public class Running extends Started {

    private Scores scores;

    public Running(Score score) {
        if(score.isStrike()){
            throw new IllegalArgumentException();
        }
        this.scores = Scores.of(score);
    }

    @Override
    public State bowl(Score score) {
       scores.bowl(score);
        if(scores.isSpare()){
            return new Spare(scores);
        }

       if(scores.isMiss()){
           return new Miss(scores);
       }
        throw new IllegalStateException();
    }

    @Override
    public int getRemainPins(){
        return scores.getRemainPins();
    }

    @Override
    public BowlingRecordState getBowlingState() {
        return BowlingRecordState.RUNNING;
    }

    @Override
    public List<Integer> getRecord() {
        return scores.getRecord();
    }
}
