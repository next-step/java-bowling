package bowling.domain.state.all;

import bowling.domain.score.Scores;

public class Spare extends AllPinsFell {
    public Spare(int leftTry, Scores scores) {
        super(leftTry, scores);
    }
}
