package bowling.domain;

public class Frame {

    private final int MAX_SCORE = 10;

    private Pins pins = new Pins();
    private Score score;

    public Frame() {

    }

    public int playGame() {
        if (pins.getCountOfPin(0) == MAX_SCORE) {
            this.score = new Score(this.pins);
            return 1;
        }

        this.pins.rollTheBallNext();
        this.score = new Score(this.pins);

        return 2;
    }

    public int playGameLast() {
        if(isLastStrike()){
            this.score = new Score(this.pins);
            return 3;
        }

        this.pins.rollTheBallNext();

        if(isLastSpare()){
            this.score = new Score(this.pins);
            return 3;
        }

        this.score = new Score(this.pins);
        return 2;
    }

    private boolean isLastStrike(){
        if (pins.getCountOfPin(0) == MAX_SCORE) {
            this.pins.getOneMoreChance();
            this.pins.rollTheBallNext();

            return true;
        }

        return false;
    }

    private boolean isLastSpare(){
        if (this.pins.getTotalScore() == 10) {
            this. pins.getOneMoreChance();
            return true;
        }
        return false;
    }

    public Pins getPins() {
        return pins;
    }

    public Score getScore() {
        return score;
    }
}
