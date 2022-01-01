package bowling.engine;


import java.util.List;

import bowling.engine.collection.FirstClassList;

public interface Bonus extends FirstClassList<BonusScores> {
    Bonus applyBonus(Shot shot);
    Score score();

    List<BonusScores> remainBonus();
    boolean completed();
}
