package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.Ready;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FinalFrame implements Frame{

    private final FrameIndex index;
    private FinalBowls bowls;

    private FinalFrame(FrameIndex index, FinalBowls bowls) {
        this.index = index;
        this.bowls = bowls;
    }

    public FinalFrame(FrameIndex index) {
        this(index, new FinalBowls());
    }

    public FinalFrame(int index) {
        this(FrameIndex.of(index), new FinalBowls());
    }

    @Override
    public Frame pitch(Pins pins) {
        bowls.pitch(pins);
        return this;
    }

    @Override
    public int getIndex() {
        return index.getIndex();
    }

    @Override
    public boolean hasNext() {
        return bowls.canProceed();
    }

    @Override
    public int score() {
        if(bowls.canProceed()){
            return Score.CANNOT_CALCULATE_SCORE;
        }
        return bowls.score();
    }

    @Override
    public int calculateAdditionalScore(Score beforeScore) {
        return calculateAdditionalScore(beforeScore, 0);
    }

    public int calculateAdditionalScore(Score score, int index){
        if(index >= getBowls().size()){
            return Score.CANNOT_CALCULATE_SCORE;
        }

        Bowl bowl = getBowls().get(index);
        if(bowl instanceof  Ready){
            return Score.CANNOT_CALCULATE_SCORE;
        }

        Score after = bowl.calculateScore(score);
        if(after.isFinished()){
            return after.getValue();
        }

        return calculateAdditionalScore(after, index+1);
    }


    @Override
    public List<Bowl> getBowls() {
        return bowls.getBowls();
    }

    @Override
    public String toString(){
        return "[final frame]" +
                "\nindex: "+index
                +"\nbowls: "+
                bowls.getBowls().stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" / "));
    }
}
