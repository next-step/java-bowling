package bowling.domain.factory;

import bowling.domain.scores.Scores;

public interface ScoresFactory {

    Scores create(int round);

    Scores create(int round, int hitCount);


}
