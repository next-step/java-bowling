package bowling.domain.status;

public class FinalThirdBowl extends FrameStatus {

    public FinalThirdBowl(int firstCountOfPin, int secondCountOfPin, int thirdCountOfPin) {
        this.firstCountOfPin = firstCountOfPin;
        this.secondCountOfPin = secondCountOfPin;
        this.thirdCountOfPin = thirdCountOfPin;
    }

    @Override
    public FrameStatus bowl(int countOfPin) {
        return null;
    }

}
