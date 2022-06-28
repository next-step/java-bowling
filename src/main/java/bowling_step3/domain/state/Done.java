package bowling_step3.domain.state;

import bowling_step3.domain.Scores;

public class Done implements Status {
    private Scores scores;

    public Done(Scores scores) {
        this.scores = scores;
    }

//    public Done(Scores scores) {
//        this.scores = scores;
//    }


    public Status pitch(int i) {
        throw new UnsupportedOperationException("cannot pitch more");
    }

    public Status pitchLast(int i) {
        return pitch(i);
    }

    public boolean isFinished() {
        return true;
    }

    @Override
    public Integer getScore() {
        return this.scores.getScore();
    }

//    @Override
//    public Status pitchLast(int numPins) {
//        return null;
//    }

    public int calculateAdditionalScore(Status status) {
        throw new UnsupportedOperationException();
    }

    public Scores scores() {
        return this.scores;
    }
}
