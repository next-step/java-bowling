package bowling.domain;

public class Player {
    private final String name;
    private Frame firstFrame;
    private Frame currentFrame;

    public Player(String playerName) {
        this.name = playerName;
    }

    public FrameStatuses bowlFirst(int numberOfHitPin) {
        Frame firstFrame = Frame.bowlFirst(numberOfHitPin);
        this.firstFrame = firstFrame;
        this.currentFrame = firstFrame;
        return calculateCurrentStatus();
    }

    public FrameStatuses bowl(int numberOfHitPin) {
        if (this.currentFrame.isCompleted()) {
            this.currentFrame = currentFrame.next(numberOfHitPin);
            return calculateCurrentStatus();
        }
        this.currentFrame = this.currentFrame.bowlSecond(numberOfHitPin);
        return calculateCurrentStatus();
    }

    public FrameStatuses calculateCurrentStatus() {
        return this.currentFrame.calculateCurrentStatus();
    }

    public int checkWhere() {
        int count = 1;
        Frame current = this.firstFrame;
        while (current.toNext() != null) {
            current = current.toNext();
            count += 1;
        }
        return count;
    }
}
