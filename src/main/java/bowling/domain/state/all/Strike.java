package bowling.domain.state.all;

import bowling.domain.score.Scores;

public class Strike extends AllPinsFell {
    public Strike(int leftTry, Scores scores) {
        super(leftTry, scores);
    }
}
