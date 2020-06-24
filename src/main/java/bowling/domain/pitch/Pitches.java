package bowling.domain.pitch;

import bowling.domain.score.Score;

import java.util.List;

public interface Pitches {
    public static final int MAXIMUM_NORMAL_PITCH_COUNTS = 2;
    public static final int MAXIMUM_FINAL_PITCH_COUNTS = 3;

    public void throwBall(Score score);

    public boolean isHavingSameCounts(int pitchCounts);

    public boolean isSpare();

    public List<String> getScoreSignatures();

    public int getPitchScoreSum();
}
