package bowling.engine;

import bowling.engine.collection.FirstClassList;

public interface BonusScores extends FirstClassList<Score> {
    int NUMBER_OF_NO_BONUS = 0;
    int NUMBER_OF_SPARE_BONUS = 1;
    int NUMBER_OF_STRIKE_BONUS = 2;

    void appendBonus(Score score);
    Score sum();
    boolean remain();
    boolean completed();
}
