package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class LastBonus extends Running {
    public LastBonus(Scores scores) {
        super(scores);
    }

    //    @Override
//    public Status pitch(int numPins) {
//        throw new UnsupportedOperationException("Unreachable");
//    }
//
    @Override
    public Status pitchLast(int numPins) {
        // s + p -> lb
        // s -> lb
        // length 1, s -> return lastB

        Scores scores = this.scores().pitchLast(numPins);
        if (this.scores().size() == 2 && this.scores().isStrike()) {
            return new LastBonus(scores);
        }
        if (scores.isStrike()) {
            return new Strike(scores);
        }
        return new Spare(scores);
    }
}
