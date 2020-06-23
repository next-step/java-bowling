package bowling.domain.pitch;

import bowling.domain.score.Score;

import java.util.List;

public interface Pitches {

    public void throwBall(Score score);

    public List<Pitch> getPitches();
}
