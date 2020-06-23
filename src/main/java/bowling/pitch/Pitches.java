package bowling.pitch;

import bowling.score.Score;

import java.util.List;

public interface Pitches {
    public static final int MAXIMUM_NORMAL_PITCH_COUNTS = 2;
    public static final int MAXIMUM_FINAL_PITCH_COUNTS = 3;

    public void throwBall(Score score);

    public List<Pitch> getPitches();
}
