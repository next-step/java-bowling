package bowling.bowlingscore.domain.frame.score;

import bowling.bowlingscore.domain.pitching.Pitching;

public class MissScore implements Score {
    @Override
    public int score(Pitching firstPitching) {
        return 0;
    }
}
