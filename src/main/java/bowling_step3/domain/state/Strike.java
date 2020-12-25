package bowling_step3.domain.state;


import bowling_step3.domain.Score;

public class Strike extends Finished {

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public int getPitchCount() {
        return 1;
    }

    @Override
    public Score getScore() {
        return Score.ofStrike();
    }

    @Override
    public int getTotalCount() {
        return 10;
    }
}
