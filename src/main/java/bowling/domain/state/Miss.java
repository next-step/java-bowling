package bowling.domain.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.ComputableScore;
import bowling.domain.score.Score;

import java.util.Arrays;
import java.util.List;

public class Miss extends End {
    private final Pins firstPins;
    private final Pins secondPins;

    private Miss(Pins firstPins, Pins secondPins) {
        this.firstPins = firstPins;
        this.secondPins = secondPins;
    }

    public static Miss of(Pins firstPins, Pins secondPins) {
        return new Miss(firstPins, secondPins);
    }

    @Override
    public boolean isMiss() {
        return true;
    }

    @Override
    public List<Integer> getHitPins() {
        return Arrays.asList(firstPins.getCountHitPins(), secondPins.getCountHitPins());
    }

    @Override
    public Score score() {
        return ComputableScore.of(firstPins.score().add(secondPins.score()));
    }

    @Override
    public Score addBonusScore(Score score) {
        Score resultScore = score.add(firstPins.score());

        if (resultScore.isCompute()) {
            return resultScore;
        }

        return resultScore.add(secondPins.score());
    }

}
