package step3;

public class Strike implements State {
    private Score score;

    public Strike() {
        this.score = new Score(10, 2);
    }
}
