package bowling.domain;

public class BowlingMachine {

    private Player player;
    private Frame frame;
    private FrameResults frameResults;

    private BowlingMachine(Player player) {
        this.player = player;
    }

    public void startGame() {
        FallenPinNumber fallenPinNumber = player.pitchBowlingBall();
        Frame frame =


    }
}
