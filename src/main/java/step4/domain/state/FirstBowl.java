package step4.domain.state;

import step4.domain.Score;

public class FirstBowl implements State {
    private int falledPins;
    private Score score;

    public FirstBowl(int falledPins) {
        this.falledPins = falledPins;
        this.score = new Score(falledPins, 1);
    }

    public State throwBowl(int falledPins) {
        if (this.falledPins + falledPins == 10) {
            return new Spair();
        }
        return new LastBowl(this.falledPins + falledPins);
    }

    public int getScore() {
        return score.getScore();
    }
}
