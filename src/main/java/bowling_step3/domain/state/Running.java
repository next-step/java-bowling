package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

abstract class Running implements Status {
    private Scores scores;

    public Running(Scores scores) {
        this.scores = scores;
    }

    public boolean isFinished() {
        return false;
    }

    public Integer getScore() {
        throw new UnsupportedOperationException("Cannot get score, it's still running.");
    }

    public int calculateAdditionalScore(Status status) {
        throw new UnsupportedOperationException("Cannot calculate, it's still running.");
    }

    ;

    public Scores scores() {
        return this.scores;
    }

    ;

    //    public Status pitch(int numPins) {
//        Scores scores = this.scores().pitch(numPins);
//        if (scores.isSpare()) {
//            return new Spare(scores);
//        }
//        return new Miss(scores);
//    }
//
//    public Status pitchLast(int numPins) {
//        Scores scores = this.scores().pitchLast(numPins);
//        if (scores.isSpare() || scores.isStrike()) {
//            return new LastBonus(scores);
//        }
//        return new Miss(scores);
//    }
    public Status pitch(int numPins) {
        Scores scores = this.scores().pitch(numPins);
        if (scores.isStrike()) {
            return new Strike();
        }
        return new FirstPitch(scores);
    }

//    public Status pitchLast(int numPins) {
//        return pitch(numPins);
//        Scores scores = this.scores().pitchLast(numPins);
//        if (scores.isStrike() || scores.isSpare()) {
//            return new LastBonus(scores);
//        }
//        return new FirstPitch(scores);
//    }
}