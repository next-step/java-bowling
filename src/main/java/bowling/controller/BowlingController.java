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
            Frame frame = frames.createFrame();
            frames.add(createFrame(frame));
        }
    }

    private Frame createFrame(Frame frame) {
        while(!frame.isFinish()) {
            int round = frames.getSize() + ONE;
            Pitch pitch = Pitch.from(InputView.inputScore(round));
            frame.add(pitch);
            ResultView.printFrame(player.getName(), frame, frames);
        }
        return frame;
    }
}
