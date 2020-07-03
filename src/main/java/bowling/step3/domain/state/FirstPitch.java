package bowling.step3.domain.state;

import bowling.step3.domain.score.Score;

public class FirstPitch extends Running{
    private static final String PITCH_LIMIT_EXCEPTION = "한 프레임의 투구 합은 10을 넘을 수 없습니다.";
    private static final int ZERO = 0;

    private Pins firstPins;

    public FirstPitch(Pins firstPins) {
        this.firstPins = firstPins;
    }

    @Override
    public State pitch(Pins pins) {
        if(firstPins.isInvalidatePins(pins)){
            throw new IllegalArgumentException(PITCH_LIMIT_EXCEPTION);
        };
        if(firstPins.isSpare(pins)){
            return new Spare(firstPins, pins);
        }
        return new Miss(firstPins, pins);
    }

    @Override
    public String display() {
        return firstPins.display();
    }

    @Override
    public Score addAdditionalScore(Score beforeScore) {
        return beforeScore.addScore(new Score(firstPins.getCountOfPins(),ZERO));
    }
}
