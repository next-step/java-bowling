package bowling.domain.score;

import java.util.ArrayList;
import java.util.List;

public class Score {

    private List<Integer> downPins;
    private ScoreType scoreType;

    private Score(List<Integer> downPins, ScoreType scoreType) {
        this.downPins = downPins;
        this.scoreType = scoreType;
    }

    public static Score create(int downPins, ScoreType scoreType) {
        ArrayList<Integer> downPinsList = new ArrayList<>();
        downPinsList.add(downPins);
        return new Score(downPinsList, scoreType);
    }

    public static Score create(List<Integer> downPins, ScoreType scoreType) {
        return new Score(downPins, scoreType);
    }

    public int getValue() {
        return downPins.stream()
            .reduce(0, Integer::sum);
    }

    public List<Integer> getDownPins() {
        return downPins;
    }

    public ScoreType getScoreType() {
        return scoreType;
    }
}
