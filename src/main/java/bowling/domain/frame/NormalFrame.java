package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.Ready;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class NormalFrame implements Frame{
    private static final int LAST_NORMAL_FRAME_INDEX = 9;

    private final int index;
    private Frame nextFrame;
    private Bowl bowl;

    private NormalFrame(int index, Bowl bowl) {
        this.index = index;
        this.bowl = bowl;
    }

    public NormalFrame(int index) {
        this(index, new Ready());
    }

    @Override
    public Frame pitch(Pins pins){
        bowl = bowl.pitch(pins);
        if(bowl.isEnd()){
            nextFrame = createNextFrame();
            return nextFrame;
        }
        return this;
    }

    private Frame createNextFrame(){
        if(index == LAST_NORMAL_FRAME_INDEX){
            return new FinalFrame(index+1);
        }
        return new NormalFrame(index+1, new Ready());
    }

    @Override
    public int score() {
        if(!bowl.isEnd()){
            return Score.CANNOT_CALCULATE_SCORE;
        }
        Score curScore = bowl.score();
        if(curScore.isFinished()){
            return curScore.getValue();
        }
        return nextFrame.calculateAdditionalScore(curScore);
    }

    @Override
    public int calculateAdditionalScore(Score beforeScore) {
        if(bowl instanceof Ready){
            return Score.CANNOT_CALCULATE_SCORE;
        }
        Score after = bowl.calculateScore(beforeScore);
        if(after.isFinished()){
            return after.getValue();
        }
        if(nextFrame == null){
            return Score.CANNOT_CALCULATE_SCORE;
        }
        return nextFrame.calculateAdditionalScore(after);
    }

    public Bowl getBowl() {
        return bowl;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String getSymbol() {
        return bowl.getSymbol();
    }

    @Override
    public String toString(){
        return "index: "+index +" | addValue: "+bowl;
    }

}
