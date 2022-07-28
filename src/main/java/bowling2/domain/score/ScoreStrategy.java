package bowling2.domain.score;

import bowling2.domain.frame.Frame;

public interface ScoreStrategy {
    int compute(Frame frame);
}
