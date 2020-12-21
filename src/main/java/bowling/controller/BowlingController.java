package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    public static final int ONE = 1;

    private Player player;
    private Frames frames;

    public void start() {

        player = InputView.inputPlayer();
        ResultView.printEmptyRecords(player.getName());

        frames = Frames.init();

        while(!frames.isFinished()) {
            frames.add(playGame());
        }
    }

    private Frame playGame() {
        if(frames.isFinalFrame()) {
            return createFinalFrame();
        }
        return createNormalFrame();
    }

    private Frame createNormalFrame() {
        Frame normalFrame = NormalFrame.init();
        return createFrame(normalFrame);
    }

    private Frame createFinalFrame() {
        Frame finalFrame = FinalFrame.init();
        return createFrame(finalFrame);
    }

    private Frame createFrame(Frame frame) {
        while(!frame.isFinish()) {
            int round = frames.getSize() + ONE;
            Pitch pitch = Pitch.from(InputView.inputScore(round));
            frame.add(pitch);
            ResultView.printFrames(player.getName(), round);
        }
        return frame;
    }
}
