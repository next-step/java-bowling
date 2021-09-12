package step3.state;

import step3.Score;

public class Spair extends Finished {
    private Score score;

    public Spair() {
        this.score = new Score(10, 1);
    }
}
