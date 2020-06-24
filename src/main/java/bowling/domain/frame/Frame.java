package bowling.domain.frame;

import bowling.domain.score.FrameNumericScore;
import bowling.domain.score.Score;

import java.util.List;

public interface Frame {
    public static final int MAXIMUM_NORMAL_PITCH_COUNTS = 2;
    public static final int MAXIMUM_FINAL_PITCH_COUNTS = 3;

    public Frame next();

    public void bowl(Score score);

    public boolean isMovableToNextFrame();

    public List<String> getScoreSignatures();

    public int getIndex();

    public FrameNumericScore calculateFrameScore(Frame lastFrame);

    public boolean isStrike();

    public int getScoresSum();

    public boolean isSpare();

}
