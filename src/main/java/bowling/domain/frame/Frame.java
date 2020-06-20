package bowling.domain.frame;

import bowling.domain.bowling.BowlingScore;
import bowling.domain.pitch.Pitches;

public interface Frame {

    public Frame next();

    public void bowl(int hitCounts);

    public int getIndex();

    public Pitches getPitches();

    public BowlingScore getScore();
}
