package bowling.domain.status;

public class FinalFirstBowl extends FrameStatus {

    public FinalFirstBowl(int firstCountOfPin) {
        this.firstCountOfPin = firstCountOfPin;
    }

    @Override
    public FrameStatus bowl(int countOfPin) {
        return new FinalSecondBowl(getFirstCountOfPin(), countOfPin);
    }


}
