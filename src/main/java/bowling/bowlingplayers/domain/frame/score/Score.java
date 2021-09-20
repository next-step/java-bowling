package bowling.bowlingplayers.domain.frame.score;

import bowling.bowlingplayers.domain.pitching.Pitching;

public interface Score {
    int score(Pitching firstPitching);
}
