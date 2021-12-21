package bowling.domain.factory;

import bowling.domain.scores.HitScores;

public interface ScoresFactory {

    HitScores create(int round);

    HitScores create(int round, int hitCount);


}
