package bowling.frame;

import bowling.score.Score;

public interface Frame {

    public Frame next();

    public void bowl(Score score);

    public boolean isMovableToNextFrame();

    public int getIndex();
}
