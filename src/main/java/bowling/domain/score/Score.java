package bowling.domain.score;

import bowling.domain.pin.Pin;

import java.util.Objects;

import static bowling.domain.pin.Pin.GUTTER_PIN_COUNT;
import static bowling.domain.pin.Pin.STRIKE_PIN_COUNT;

public class Score {
    private final ScoreKind scoreKind;
    private final Pin pin;

    private Score(ScoreKind scoreKind, int pin) {
        this.scoreKind = scoreKind;
        this.pin = Pin.of(pin);
    }

    public static Score defaultScore(Scores scores, int pin) {
        return new Score(findKindAtDefaultScore(scores, pin), pin);
    }

    public static Score lastScore(Scores scores, int pin) {
        return new Score(findKindAtLastScore(scores, pin), pin);
    }

    public int getPin() {
        return pin.getPin();
    }

    public boolean isEqualKind(ScoreKind scoreKind) {
        return this.scoreKind.equals(scoreKind);
    }

    public String pinToScore() {
        return scoreKind.pinToScore(getPin());
    }

    public boolean isClear() {
        return scoreKind.isClear(scoreKind);
    }

    private static ScoreKind findKindAtDefaultScore(Scores scores, int pin) {
        if (scores.isFirstPlay() && isStrikePin(pin)) {
            return ScoreKind.STRIKE;
        }
        return findScoreKind(scores, pin);
    }

    private static ScoreKind findKindAtLastScore(Scores scores, int pin) {
        if (isStrikePin(pin)) {
            return ScoreKind.STRIKE;
        }
        return findScoreKind(scores, pin);
    }

    private static ScoreKind findScoreKind(Scores scores, int pin) {
        if (scores.isSecondPlay() && isStrikePin(scores.currentPinCount() + pin)) {
            return ScoreKind.SPARE;
        }
        if (isGutterPin(pin)) {
            return ScoreKind.GUTTER;
        }
        return ScoreKind.MISS;
    }

    private static boolean isStrikePin(int pin) {
        return pin == STRIKE_PIN_COUNT;
    }

    private static boolean isGutterPin(int pin) {
        return pin == GUTTER_PIN_COUNT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score that = (Score) o;
        return scoreKind == that.scoreKind &&
                Objects.equals(pin, that.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoreKind, pin);
    }
}
