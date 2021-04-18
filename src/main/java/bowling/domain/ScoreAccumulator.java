package bowling.domain;

public class ScoreAccumulator {

    private int accumulateScore;

    public int sum(int calculateOfScore) {
        accumulateScore += calculateOfScore;
        return accumulateScore;
    }
}
