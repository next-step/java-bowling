package bowling.domain;

public class BowlingMachine {

    private static final int GAME_FRAME_NUMBER = 10;

    private Player player;
    private Frame frame;
    private FrameResults frameResults;

    private BowlingMachine(Player player) {
        this.player = player;

    }

    public Frame startGame() {
        Frame firstFrame = NormalFrame.of(FrameNumber.of(1));
        Frame tempFrame = firstFrame;
        for (int i = 2; i <= GAME_FRAME_NUMBER; i++) {
            FallenPinNumber firstFallenPinNumber = player.pitchBowlingBall();
            Frame currentFrame = tempFrame.figureOutFrame(firstFallenPinNumber);
            if (currentFrame.equals(tempFrame)) {
                FallenPinNumber secondFallenPinNumber = player.pitchBowlingBall();
                tempFrame = currentFrame.figureOutFrame(secondFallenPinNumber);
            }
        }
        frame = firstFrame;
        return frame;
    }
}
