package bowling.domain.status;

public class Ready extends FrameStatus{

    private boolean isFinal;

    public Ready(boolean isFinal) {
        this.isFinal = isFinal;
    }

    @Override
    public FrameStatus bowl(int countOfPin) {
        if (isFinal) {
            return new FinalFirstBowl(countOfPin);
        }

        if (countOfPin == 10) {
            return new Strike();
        }

        return new FirstBowl(countOfPin);
    }

}
