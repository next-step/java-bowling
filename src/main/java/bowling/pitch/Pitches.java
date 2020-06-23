package bowling.pitch;

import bowling.score.Score;

import java.util.List;

public interface Pitches {

    public void throwBall(Score score);

    public List<Pitch> getPitches();
}
