package bowling.domain;

import static bowling.BowlingApp.getPinCalculateStrategy;

public class NormalFrame extends DefaultFrame {

    public NormalFrame(GeneralScore generalScore) {
        super(generalScore);
        if(generalScore.size() > 2) {
            throw new IllegalArgumentException("score size cannot be bigger than 2");
        }
    }

    public NormalFrame(GeneralScore generalScore, int order) {
        super(generalScore, order);
    }

    @Override
    public Frame nextRound(){
        int nextOrder = order + 1;
        if(nextOrder == 10) {
            return new FinalFrame(new GeneralScore(getPinCalculateStrategy()), nextOrder);
        }
        return new NormalFrame(new GeneralScore(getPinCalculateStrategy()), nextOrder);
    }



}
