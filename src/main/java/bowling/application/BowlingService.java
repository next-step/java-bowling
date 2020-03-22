package bowling.application;

import bowling.domain.Bowling;

public class BowlingService {

    public Bowling bowl(Bowling bowling, int hit) {
        bowling.bowl(hit);
        return bowling;
    }
}
