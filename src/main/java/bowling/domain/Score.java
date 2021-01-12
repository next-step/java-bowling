package bowling.domain;

import bowling.util.ErrorMessage;

public class Score {
    private final static int MAX_SCORE = 10;
    private Pins pins;

    public Score(final Pins pins) {
        checkNull(pins);
        this.pins = pins;
    }

    public String getFirstScore() {
        if (pins.getCountOfPin(0) == MAX_SCORE) {
            return ScoreTypeEnum.STRIKE.getScoreType();
        }

        return Integer.toString(pins.getCountOfPin(0));
    }

    public String getNextScore(final int chanceIndex) {
        if(isStrike(chanceIndex)){
            return ScoreTypeEnum.STRIKE.getScoreType();
        }

        if (isSpare(chanceIndex)) {
            return ScoreTypeEnum.SPARE.getScoreType();
        }

        if (isGutter(chanceIndex)) {
            return ScoreTypeEnum.GUTTER.getScoreType();
        }

        return Integer.toString(pins.getCountOfPin(chanceIndex));
    }

    private boolean isStrike(final int chanceIndex) {
        if(pins.getCountOfPin(chanceIndex) == MAX_SCORE){
            return true;
        }

        return false;
    }

    private boolean isSpare(final int chanceIndex) {
        if (pins.plusBeforePinAndNowPin(chanceIndex) == MAX_SCORE) {
            return true;
        }

        return false;
    }

    private boolean isGutter(final int chanceIndex){
        if (pins.plusBeforePinAndNowPin(chanceIndex) == 0) {
            return true;
        }

        return false;
    }

    private void checkNull(final Pins pins){
        if(pins == null){
            throw new NullPointerException(ErrorMessage.getCheckNull());
        }
    }
}
