package bowling.domain.status;

public class FirstBowl extends FrameStatus {

    FirstBowl(int countOfPin) {
        this.firstCountOfPin = countOfPin;
    }

    @Override
    public FrameStatus bowl(int countOfPin) {
        if (this.firstCountOfPin + countOfPin == 10) {
            return new Spare(this.firstCountOfPin, countOfPin);
        }

        return new Miss(this.firstCountOfPin, countOfPin);
    }

}
