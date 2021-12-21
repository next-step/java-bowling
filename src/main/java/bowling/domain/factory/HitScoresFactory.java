package bowling.domain.factory;

import bowling.domain.scores.HitScores;

public interface HitScoresFactory {

    HitScores create(int round);

    HitScores create(int round, int hitCount);


}
