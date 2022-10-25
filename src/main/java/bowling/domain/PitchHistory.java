package bowling.domain;

public class PitchHistory {
    private final PitchResultEnum result;
    private final int downPin;

    public PitchHistory(PitchResultEnum result, int downPin) {
        this.result = result;
        this.downPin = downPin;
    }

    public PitchResultEnum getResult() {
        return result;
    }

    public int getDownPin() {
        return downPin;
    }

}
