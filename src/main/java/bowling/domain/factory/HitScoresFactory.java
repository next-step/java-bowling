package bowling.domain.factory;

import bowling.Frames;
import bowling.domain.scores.HitScores;

public interface HitScoresFactory {

    HitScores create(int round);

    HitScores create(int round, int hitCount);

    HitScores create(Frames frames, int hitCount);

}
