package bowling.controller;

import bowling.domain.*;
import bowling.view.InputView;
import bowling.view.ResultView;

public class BowlingController {

    private static final int ONE = 1;
    private static final int INITIAL_SCORE = 0;
    private static final int NORMAL_CHANCE = 2;
    private static final int FINAL_CHANCE = 2;
    private static final int FINAL_ROUND = 10;

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
            Pitch pitch = initPitch(round);
            frame.playPitch(pitch.bowl(InputView.inputScore(round)));
            ResultView.printFrame(player.getName(), frame, frames);
        }
        return frame;
    }

    private Pitch initPitch(int round) {
        if(round >= FINAL_ROUND) {
            return Pitch.of(INITIAL_SCORE, FINAL_CHANCE);
        }
        return Pitch.of(INITIAL_SCORE, NORMAL_CHANCE);
    }
}
