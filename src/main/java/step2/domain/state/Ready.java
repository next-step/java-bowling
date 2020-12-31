package step2.domain.state;

import step2.domain.Pitch;
import step2.domain.Score;

public class Ready extends Running {

    @Override
    public State bowl(int fallingPins) {
        Pitch pitch = Pitch.from(fallingPins);
        if (pitch.isStrike()) {
            return new Strike();
        }
        return new FirstBowl(pitch);
    }

    @Override
    public Score calculateAdditionalScore(Score score) {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }

}
