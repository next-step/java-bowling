package bowling.frame;

import bowling.score.Score;

public interface Frame {
    public static final int MAXIMUM_NORMAL_PITCH_COUNTS = 2;
    public static final int MAXIMUM_FINAL_PITCH_COUNTS = 3;

    public Frame next();

    public void bowl(Score score);

    public boolean isMovableToNextFrame();

    public int getIndex();
}
