package bowling.domain;

public class PitchHistory {
    private final PitchResult result;
    private final int downPin;

    public PitchHistory(PitchResult result, int downPin) {
        this.result = result;
        this.downPin = downPin;
    }

    public PitchResult getResult() {
        return result;
    }

    public int getDownPin() {
        return downPin;
    }

}
