package bowling.service;

public class BowlingService {

    private int frameNumber = 0;

    public boolean isLast() {
        return frameNumber == 10;
    }

    public void bowl(String pins) {

    }

    public int tryingCount() {
        return 0;
    }
}
