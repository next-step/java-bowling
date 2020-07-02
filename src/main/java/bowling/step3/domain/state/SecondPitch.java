package bowling.step3.domain.state;

import bowling.step3.domain.score.Score;

public abstract class SecondPitch extends Finished{
    protected static final String DELIMITER = "|";
    protected static final int ZERO = 0;

    private Pins firstPins;
    private Pins secondPins;

    public SecondPitch(Pins firstPins, Pins secondPins){
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public Pins getFirstPins() {
        return firstPins;
    }

    public Pins getSecondPins() {
        return secondPins;
    }

    @Override
    public Score addAdditionalScore(Score beforeScore) {
        Score score = beforeScore.addScore(new Score(firstPins.getCountOfPins(), ZERO));
        if (score.isFinishedScore()){
            return score;
        }
        return score.addScore(new Score(secondPins.getCountOfPins(), ZERO));
    }
}
