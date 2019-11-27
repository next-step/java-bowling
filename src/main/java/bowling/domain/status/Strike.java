package bowling.domain.status;

public class Strike extends FrameStatus {

    @Override
    public FrameStatus bowl(int countOfPin) {
        if (countOfPin == 10) {
            return new Strike();
        }

        return new Miss(10, countOfPin);
    }
}
