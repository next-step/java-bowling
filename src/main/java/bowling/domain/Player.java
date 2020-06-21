package bowling.domain;

public class Player {
    private final String name;
    private NormalFrame firstNormalFrame;
    private NormalFrame currentNormalFrame;

    public Player(String playerName) {
        this.name = playerName;
    }

    public FrameStatuses bowlFirst(int numberOfHitPin) {
        NormalFrame firstNormalFrame = NormalFrame.bowlFirst(numberOfHitPin);
        this.firstNormalFrame = firstNormalFrame;
        this.currentNormalFrame = firstNormalFrame;
        return calculateCurrentStatus();
    }

    public FrameStatuses bowl(int numberOfHitPin) {
        if (this.currentNormalFrame.isCompleted()) {
            this.currentNormalFrame = currentNormalFrame.next(numberOfHitPin);
            return calculateCurrentStatus();
        }
        this.currentNormalFrame.bowlSecond(numberOfHitPin);
        return calculateCurrentStatus();
    }

    public FrameStatuses calculateCurrentStatus() {
        return this.currentNormalFrame.calculateCurrentStatus();
    }

    public int checkWhere() {
        int count = 1;
        NormalFrame current = this.firstNormalFrame;
        while (current.toNext() != null) {
            current = current.toNext();
            count += 1;
        }
        return count;
    }
}
