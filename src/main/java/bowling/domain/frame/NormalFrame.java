package bowling.domain.frame;

import bowling.engine.Frame;
import bowling.engine.Result;
import bowling.engine.Sequence;
import bowling.engine.Shot;

public class NormalFrame extends BowlingFrame {

    protected NormalFrame(Sequence sequence, Result result) {
        super(sequence, result);
    }

    @Override
    public Frame nextShot(Shot shot) {
        if (completed()) {
            return of(sequence.next(), result.next(shot));
        }

        return of(sequence, result.next(shot));
    }

    @Override
    public boolean remainBonus() {
        return result.remainBonus();
    }

    @Override
    public boolean isFinal() {
        return false;
    }
}
