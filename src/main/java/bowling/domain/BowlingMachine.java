package bowling.domain;

import bowling.view.BowlingView;

import java.util.Objects;

public class BowlingMachine {

    private Player player;

    private BowlingMachine(Player player) {
        this.player = player;
    }

    public static BowlingMachine of(Player player) {
        return new BowlingMachine(player);
    }

    public void startGame() {
        Frame frame = NormalFrame.of(FrameNumber.of(1));
        Frame tempFrame = frame;
        for (int i = 1; i <= 11; i++) {

            if (tempFrame == null) {
                break;
            }

            BowlingView.outputFrameNumberPitching(manipulateViewNumber(i));
            FallenPinNumber firstFallenPinNumber = player.pitchBowlingBall();
            Frame currentFrame = tempFrame.figureOutFrame(firstFallenPinNumber);
            BowlingView.displayPitchingView(player, frame);
            if (Objects.equals(currentFrame, tempFrame)) {
                BowlingView.outputFrameNumberPitching(manipulateViewNumber(i));
                FallenPinNumber secondFallenPinNumber = player.pitchBowlingBall();
                tempFrame = currentFrame.figureOutFrame(secondFallenPinNumber);
                BowlingView.displayPitchingView(player, frame);
                continue;
            }
            tempFrame = currentFrame;
        }
    }

    private int manipulateViewNumber(int i) {
        return i >= 10 ? 10 : i;
    }
}
