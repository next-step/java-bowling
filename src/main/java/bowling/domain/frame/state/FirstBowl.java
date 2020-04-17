package bowling.domain.frame.state;

import bowling.domain.pin.Pins;
import bowling.domain.score.Score;

public class FirstBowl implements State, Calculable {
    private final Pins first;

    FirstBowl(Pins first) {
        this.first = first;
    }

    @Override
    public State roll(final Pins second) {
        Pins knockOverPins = first.add(second);

        if (knockOverPins.isSpare()) {
            return new Spare(second);
        }

        if (second.isGutter()) {
            return new SecondGutter();
        }

        return new Miss(second);
    }

    @Override
    public boolean isTurnOver() {
        return Boolean.FALSE;
    }

    @Override
    public String toResult() {
        return String.valueOf(first.count());
    }

    @Override
    public int getKnockOverCount() {
        return first.count();
    }

    @Override
    public Score getScore() {
        return new Score(first.count(), Score.ZERO);
    }
}
