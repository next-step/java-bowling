package bowling2.domain.score;

import bowling2.domain.frame.Frame;

public class StrikeScoreStrategy implements ScoreStrategy {
    @Override
    public int compute(Frame frame) {
        return 0;
    }
}
