package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

import static bowling_step3.domain.Scores.MAX_SCORE;

public class Strike extends Done {
    public Strike() {
        super(Scores.strike());
    }

    public Strike(Scores scores) {
        super(scores);
    }

    public int calculateAdditionalScore(Status status) {
        if (status instanceof Strike) {
            return MAX_SCORE + status.scores().sumOfTwo() * 2;
        }
//        || status instanceof Strike || status.scores().getFirstScore() == 10
        if (status.scores().size() < 2 ) {
            throw new UnsupportedOperationException("not yet to calculate.");
        }
        return MAX_SCORE + status.scores().sumOfTwo();
    }

    @Override
    public Status pitchLast(int numPins) {
        Scores scores = this.scores().pitchLast(numPins);
//        if (this.scores().done()) {
//            return new Done(this.scores());
//        }
        if (scores.isStrike()) {
            return new Strike(scores);
        }
        return new LastBonus(scores);
    }
}
