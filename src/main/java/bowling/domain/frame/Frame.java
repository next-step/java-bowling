package bowling.domain.frame;

import bowling.domain.score.FrameNumericScore;
import bowling.domain.score.Score;

import java.util.List;

public interface Frame {
    public static final int FIRST_INDEX = 1;
    public static final int NEXT_INDEX = 1;
    public static final int PITCH_COUNT_FOR_CALCULATING_STRIKE = 2;
    public static final int PITCH_COUNT_FOR_CALCULATING_SPARE = 1;

    public Frame next();

    public void bowl(Score score);

    public FrameNumericScore calculateFrameScore(Frame lastFrame);

    public boolean isMovableToNextFrame();

    public boolean isStrike();

    public boolean isSpare();

    public List<String> getScoreSignatures();

    public int getPitchScoreSum();

    public int getIndex();
}
