package bowling_step3.domain;

import java.util.List;

public abstract class Frame {

    public abstract void add(Pitch pitch);

    public abstract int size();

    public abstract int getFirstOfKnockDown();

    public abstract int getSecondOfKnockDown();

    public abstract boolean isFirst();

    public abstract ScoreResult getScoreResult();

    public abstract List<Pitch> getNextFramePitchPoints(int count);
}
