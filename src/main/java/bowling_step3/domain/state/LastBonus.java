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
        boolean secondPhase = this.scores().size() == 1;
        Scores scores = this.scores().pitchLast(numPins);
        if (secondPhase) {
            return new LastBonus(scores);
        }
        return new GameOver(scores);
    }
}
