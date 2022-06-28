package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class LastBonus extends Running {
    public LastBonus(Scores scores) {
        super(scores);
    }

    @Override
    public Status pitch(int numPins) {
        throw new UnsupportedOperationException("Unreachable");
    }

    @Override
    public Status pitchLast(int numPins) {
        Scores scores = this.scores().pitchLast(numPins);
        return new Done(scores) ;
    }
}
