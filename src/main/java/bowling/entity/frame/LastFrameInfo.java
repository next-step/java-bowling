package bowling.entity.frame;

import bowling.entity.Pin;
import bowling.entity.score.*;

import java.util.LinkedList;

public class LastFrameInfo implements FrameInfo {
    private static final int MAX_BOWL_COUNT = 3;
    private static final int DEFAULT_BOWL_COUNT = 2;

    private final LinkedList<Pin> pins;
    private int maxBowlCount;

    public LastFrameInfo() {
        this.pins = new LinkedList<>();
        this.maxBowlCount = DEFAULT_BOWL_COUNT;
    }

    @Override
    public ScoreType bowl(Pin fallenPin) {
        pins.add(fallenPin);
        ScoreType scoreType = getScoreType();

        if (scoreType instanceof Strike || scoreType instanceof Spare) {
            this.maxBowlCount = MAX_BOWL_COUNT;
        }

        return scoreType;
    }

    private ScoreType getScoreType() {
        Pin sumPin;
        int sum = 0;
        ScoreType scoreType = new None();
        LinkedList<Pin> lastFramePins = new LinkedList<>();

        for (Pin pin : pins) {
            sum += pin.pin();
            sumPin = new Pin(sum);
            lastFramePins.add(sumPin);
            scoreType = ScoreTypeEnum.findScoreType(lastFramePins.size(), sumPin.isTenPin()).createLastFrameScoreType(lastFramePins);

            lastFramePins = lastFramePins(scoreType, lastFramePins);
            sum = lastFramePinsSum(scoreType, sum);

        }

        return scoreType;
    }

    private LinkedList<Pin> lastFramePins(ScoreType scoreType, LinkedList<Pin> lastFramePins){
        if (scoreType.isFrameEnd()) {
            return new LinkedList<>();
        }

        return lastFramePins;
    }

    private int lastFramePinsSum(ScoreType scoreType, int sum){
        if (scoreType.isFrameEnd()) {
            return 0;
        }

        return sum;
    }

    @Override
    public String scoreResult() {
        return getScoreType().scoreResult();
    }

    public boolean isFrameEnd() {
        return getScoreType().isFrameEnd();
    }

    public boolean bowlingGameEnd() {
        return maxBowlCount == pins.size();
    }
}
