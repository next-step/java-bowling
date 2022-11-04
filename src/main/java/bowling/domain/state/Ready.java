package bowling.domain.state;

import bowling.domain.Pin;
import bowling.domain.ScoreV2;

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
    public ScoreV2 getScore() {
        return new ScoreV2(0, 2);
    }

    @Override
    public ScoreV2 calculateAdditionalScore(ScoreV2 scoreV2) {
        return scoreV2;
    }

    public String describe() {
        return "";
    }
}
