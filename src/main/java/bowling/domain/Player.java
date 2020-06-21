package bowling.domain;

public class Player {
    private final String name;
    private Frame frame;

    public Player(String playerName) {
        this.name = playerName;
    }

    public FrameStatus bowlFirst(int numberOfHitPin) {
        this.frame = Frame.bowlFirst(numberOfHitPin);
        return this.frame.calculateCurrentStatus();
    }

    public FrameStatus bowl(int secondNumberOfHitPin) {
        Frame nextFrame = frame.next(secondNumberOfHitPin);
        return nextFrame.calculateCurrentStatus();
    }
}
