package bowling.domain.state;

import bowling.domain.Score;

import java.util.LinkedList;


public class Strike extends Finished{


    public Strike(LinkedList<Score> scores) {
        super(scores);
    }

    @Override
    public State bowl(Score score) {
        return null;
    }

    @Override
    public int bonusCount() {
        return 0;
    }
}
