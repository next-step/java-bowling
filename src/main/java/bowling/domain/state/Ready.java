package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.Score;

import java.util.List;

public class Ready extends State {

    @Override
    public State bowl(Pin pin) {
        if (pin.isKnockDown()) {
            return new Strike();
        }

        return new FirstBowl(pin);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Score getScore() {
        return new Score(0, 2);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return score;
    }

    @Override
    public List<Pin> pins() {
        return List.of();
    }
}
