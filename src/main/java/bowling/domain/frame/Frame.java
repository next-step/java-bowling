package bowling.domain.frame;

import bowling.domain.score.FrameScore;
import bowling.domain.score.Score;

import java.util.List;

public interface Frame {
    public static final int ZERO_INDEX = 0;
    public static final int MAXIMUM_NORMAL_FRAME_INDEX = 9;

    public Frame next(int index);

    public void bowl(Score score);

    public boolean isFinished();

    public FrameScore calculateFrameScore();

    public FrameScore delegateCalculation(FrameScore frameScore);

    public List<String> getScoreSignatures();
}
