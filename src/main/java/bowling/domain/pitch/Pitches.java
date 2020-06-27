package bowling.domain.pitch;

import bowling.domain.score.Score;

import java.util.List;

public interface Pitches {
    public static final int MAXIMUM_NORMAL_PITCH_COUNTS = 2;
    public static final int MAXIMUM_FINAL_PITCH_COUNTS = 3;

    public void throwBall(Score score);

    public boolean hasSamePitchCounts(int pitchCounts);

    public boolean hasStrike();

    public boolean hasSpare();

    public List<String> getScoreSignatures();

    public int getPitchScoreSum();

    public int getPitchScoreByIndex(int index);

    public int getPitchCounts();
}
