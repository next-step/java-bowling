package bowling.domain.frame;

import bowling.domain.Score;

public abstract class Frame {

    public abstract void makeScore(Score firstScore, int index);

    public abstract String convert();

    public abstract boolean isSpare(Score scoreA, Score scoreB);

    public abstract boolean isLastFrame();

    public abstract boolean hasDoneFirstPitch();

    public abstract boolean hasDoneSecondPitch();

    public abstract boolean isEmpty();

    public abstract boolean isStrike();
}
