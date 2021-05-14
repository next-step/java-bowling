package bowling.entity.score;

import bowling.entity.Score;

public class None extends OnGoing {
    @Override
    public String scoreResult() {
        return "";
    }

    @Override
    public Score score() {
        return new Score(0, 0);
    }

    @Override
    public Score calculate(Score beforeScore) {
        throw new CalculateImPossibleException();
    }

}
