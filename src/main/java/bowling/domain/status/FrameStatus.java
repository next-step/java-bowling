package bowling.domain.status;

public abstract class FrameStatus {

    protected int firstCountOfPin;
    protected int secondCountOfPin;
    protected int thirdCountOfPin;

    public abstract FrameStatus bowl(int countOfPin);

    public int getFirstCountOfPin() {
        return firstCountOfPin;
    }

    public int getSecondCountOfPin() {
        return secondCountOfPin;
    }

    public int getThirdCountOfPin() {
        return thirdCountOfPin;
    }

    public boolean isEnd() {
        return false;
    }

}
