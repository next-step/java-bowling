package step3.state;

import step3.Pins;
import step3.Score;

public class Spair extends Finished {
    private Score score;
    private Pins firstOfPin;
    private Pins secondOfPin;

    public Spair(Pins firstOfPin, Pins secondOfPins) {
        this.score = new Score(10, 1);
        this.firstOfPin = firstOfPin;
        this.secondOfPin = secondOfPins;
    }


}
