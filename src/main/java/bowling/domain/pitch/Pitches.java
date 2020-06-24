package bowling.domain.pitch;

import bowling.domain.score.Score;

import java.util.List;

public interface Pitches {

    public void throwBall(Score score);

    public boolean isFinished(int pitchCounts);

    public List<String> getScoreSignatures();
}
