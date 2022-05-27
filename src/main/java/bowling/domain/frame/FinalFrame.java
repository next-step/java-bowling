package bowling.domain.frame;

import bowling.domain.bowl.Bowl;
import bowling.domain.bowl.Ready;
import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

import java.util.Objects;
import java.util.stream.Collectors;

public class FinalFrame implements Frame{

    private final int index;
    private FinalBowls bowls;

    private FinalFrame(int index, FinalBowls bowls) {
        this.index = index;
        this.bowls = bowls;
    }

    public FinalFrame(int index) {
        this(index, new FinalBowls());
    }

    @Override
    public Frame pitch(Pins pins) {
        bowls.pitch(pins);
        return this;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public boolean hasNext() {
        return bowls.canProceed();
    }

    @Override
    public String getSymbol() {
        return bowls.getBowls().stream()
                .map(Bowl::getSymbol)
                .collect(Collectors.joining("|"));
    }

    @Override
    public int score() {
        if(bowls.canProceed()){
            return Score.CANNOT_CALCULATE_SCORE;
        }
        return bowls.getBowls()
                .stream()
                .map(Bowl::score)
                .mapToInt(Score::getValue)
                .sum();
    }

    @Override
    public int calculateAdditionalScore(Score beforeScore) {
        Score score = new Score(beforeScore.getValue(), beforeScore.getLeft());
        for (Bowl curBowl : bowls.getBowls()) {
            if(curBowl instanceof Ready){
                return Score.CANNOT_CALCULATE_SCORE;
            }
            score = curBowl.calculateScore(score);
            if (score.isFinished()) {
                return score.getValue();
            }
        }
        return Score.CANNOT_CALCULATE_SCORE;
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
