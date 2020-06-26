package bowling.domain;

import bowling.Validator;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class BowlingGame {
    private List<Frame> frames = new ArrayList<>();
    private String playerName;

    public BowlingGame(String playerName) {
        this.playerName = playerName;
        ResultView.printEmptyScoreBoard(playerName);
    }

    public void start() {
        Frame frame = new BasicFrame(Frame.FIRST_FRAME_NUMBER);
        playFrame(frame);
    }

    private void playFrame(Frame frame) {
        frames.add(frame);
        frame.firstTry(Validator.checkHitPinCount(InputView.getHitPinCount(frame.getFrameNumber())));
        ResultView.printScoreBoard(playerName, frames);

        if (frame.isAbleSecondTry()) {
            frame.secondTry(Validator.checkHitPinCount(InputView.getHitPinCount(frame.getFrameNumber())));
            ResultView.printScoreBoard(playerName, frames);
        }

        if (frame.isFinalFrame()) {
            playFinalFrame(frame, frame.getFrameNumber());
        }

        BasicFrame basicFrame = (BasicFrame) frame;
        playFrame(basicFrame.nextFrame());
    }

    private void playFinalFrame(Frame frame, int frameNumber) {
        FinalFrame finalFrame = (FinalFrame) frame;
        if (finalFrame.isAbleBonusTry()) {
            finalFrame.bonusTry(Validator.checkHitPinCount(InputView.getHitPinCount(frameNumber)));
        }
        ResultView.printScoreBoard(playerName, frames);
        System.exit(0);
    }
}
