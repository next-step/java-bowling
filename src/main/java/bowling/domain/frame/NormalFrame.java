package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.Ready;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;
import java.util.List;

public class NormalFrame implements Frame{
    private static final int LAST_NORMAL_FRAME_INDEX = 9;

    private final FrameIndex index;
    private Frame nextFrame;
    private Bowl bowl;

    private NormalFrame(FrameIndex index, Bowl bowl) {
        this.index = index;
        this.bowl = bowl;
    }

    public NormalFrame(FrameIndex index) {
        this(index, new Ready());
    }

    public NormalFrame(int index) {
        this(FrameIndex.of(index), new Ready());
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
        if(index.isLastNormalIndex()){
            return new FinalFrame(index.nextIndex());
        }
        return new NormalFrame(index.nextIndex(), new Ready());
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

    @Override
    public List<Bowl> getBowls() {
        return List.of(bowl);
    }

    @Override
    public int getIndex() {
        return index.getIndex();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public String toString(){
        return "index: "+index +" | addValue: "+bowl;
    }

}
