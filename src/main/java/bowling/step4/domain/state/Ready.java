package bowling.step4.domain.state;

import bowling.step4.domain.score.Score;

public class Ready extends Running {
    private static final String DEFAULT = "";

    @Override
    public State pitch(Pins pins) {
        if(pins.isStrike()) {
            return new Strike();
        }
        return new FirstPitch(pins);
    }

    @Override
    public String display() {
        return DEFAULT;
    }

    @Override
    public Score addAdditionalScore(Score prevScore) {
        return Score.readyScore();
    }
}
