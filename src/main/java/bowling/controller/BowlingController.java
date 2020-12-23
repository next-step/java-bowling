package bowling.controller;

import bowling.domain.*;
import bowling.domain.state.State;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    private static final int ONE = 1;

    private Player player;
    private Frames frames;

    public void start() {

        player = InputView.inputPlayer();
        ResultView.printEmptyRecords(player.getName());

        frames = Frames.init();

        while(!frames.isFinished()) {
            Frame frame = frames.createFrame();
            frames.add(runningFrame(frame));
        }
    }

    private Frame runningFrame(Frame frame) {
        while(!frame.isFinish()) {
            int round = frames.getSize() + ONE;
            Pitch pitch = Pitch.from(InputView.inputScore(round));
            frame.playPitch(pitch);
            frame.bowl(pitch);
            ResultView.printFrame(player.getName(), frame, frames);
        }
        return frame;
    }
}
