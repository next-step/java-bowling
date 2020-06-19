package bowling.domain.frame;

import bowling.domain.pitch.Pitches;

public interface Frame {

    public void bowl(int hitCounts);

    public Frame next();

    public int getIndex();

    public Pitches getPitches();
}
