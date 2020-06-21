package bowling.domain;

public class Player {
    private final String name;
    private Frame currentFrame;

    public Player(String playerName) {
        this.name = playerName;
    }

    public FrameStatus bowlFirst(int numberOfHitPin) {
        this.currentFrame = Frame.bowlFirst(numberOfHitPin);
        return this.currentFrame.calculateCurrentStatus();
    }

    public FrameStatus bowl(int numberOfHitPin) {
        if (this.currentFrame.isCompleted()) {
            this.currentFrame = currentFrame.next(numberOfHitPin);
            return calculateCurrentStatus();
        }
        this.currentFrame = this.currentFrame.bowlSecond(numberOfHitPin);
        return calculateCurrentStatus();
    }

    public FrameStatus calculateCurrentStatus() {
        return this.currentFrame.calculateCurrentStatus();
    }
}
