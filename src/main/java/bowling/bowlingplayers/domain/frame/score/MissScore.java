package bowling.bowlingplayers.domain.frame.score;

import bowling.bowlingplayers.domain.pitching.Pitching;

public class MissScore implements Score {
    @Override
    public int score(Pitching firstPitching) {
        return firstPitching.scoreToNextPitching();
    }
}
