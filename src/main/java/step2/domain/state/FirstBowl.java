package step2.domain.state;

import step2.domain.Pitch;
import step2.domain.Score;

public class FirstBowl extends Running {

    private Pitch firstPitch;

    public FirstBowl(Pitch pitch) {
        this.firstPitch = pitch;
    }

    @Override
    public State bowl(int fallingPins) {
        return null;
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
