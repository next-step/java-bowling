package bowling.bowlingscore.domain.frame.score;

import bowling.bowlingscore.domain.pitching.Pitching;

public interface Score {
    int score(Pitching firstPitching);
}
