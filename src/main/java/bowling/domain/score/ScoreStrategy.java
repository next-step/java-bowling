package bowling.domain.score;

import bowling.domain.frame.Frame;

public interface ScoreStrategy {
    int compute(Frame frame);
}
